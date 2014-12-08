package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*
import groovy.time.TimeCategory;

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.HealthcareFacility
import org.callistasoftware.schedulr.domain.Performer
import org.callistasoftware.schedulr.types.Caretype
import org.callistasoftware.schedulr.types.Timetype

import riv.interoperability.headers._1.ActorType
import se.riv.crm.scheduling.getavailabletimeslots.v1.rivtabp21.GetAvailableTimeslotsResponderInterface
import se.riv.crm.scheduling.getavailabletimeslotsresponder.v1.GetAvailableTimeslotsResponseType
import se.riv.crm.scheduling.getavailabletimeslotsresponder.v1.GetAvailableTimeslotsType
import se.riv.crm.scheduling.v1.TimeslotType

@WebService(serviceName = "GetAvailableTimeslotsResponderService",
endpointInterface = "se.riv.crm.scheduling.getavailabletimeslots.v1.rivtabp21.GetAvailableTimeslotsResponderInterface",
portName = "GetAvailableTimeslotsPort",
targetNamespace = "urn:riv:crm:scheduling:GetAvailableTimeslots:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/GetAvailableTimeslotsInteraction/GetAvailableTimeslotsInteraction_1.1_RIVTABP21.wsdl")
class GetAvailableTimeslotsService implements GetAvailableTimeslotsResponderInterface {
	
	
	@Override
	public GetAvailableTimeslotsResponseType getAvailableTimeslots(String logicalAddress,
			ActorType actor, GetAvailableTimeslotsType getAvailableTimeslots) {
		log.info """
				GetAvailableTimeslots 
				logicalAddress: $logicalAddress
				healthcareFacility: $getAvailableTimeslots.healthcareFacility
				"""
		hasText(getAvailableTimeslots.healthcareFacility, 'missing argument "healthcareFacility"')
		hasText(getAvailableTimeslots.startDateInclusive, 'missing argument "startDateInclusive"')
		hasText(getAvailableTimeslots.endDateInclusive, 'missing argument "endDateInclusive"')
		hasText(getAvailableTimeslots.subjectOfCare, "missing argument \"subjectOfCare\"")
		isTrue(logicalAddress == getAvailableTimeslots.healthcareFacility, '"logicalAddress" differs from "healthcare_facility"')
			
		GetAvailableTimeslotsResponseType resp = new GetAvailableTimeslotsResponseType();
		def healthcareFacilityInstance = HealthcareFacility.findByHealthcareFacility(getAvailableTimeslots.healthcareFacility)
		if (healthcareFacilityInstance) {
			def start = Date.parse('yyyyMMdd', getAvailableTimeslots.startDateInclusive)
			def end = Date.parse('yyyyMMdd', getAvailableTimeslots.endDateInclusive)
			def buildPerformerDates = { p ->
				(start..end).each() { d ->
					log.debug "${d.format('yyyy-MM-dd')}"
					Timetype.values().each() { tt ->
						if ((!getAvailableTimeslots.timeTypeID && !getAvailableTimeslots.timeTypeName)
						|| (getAvailableTimeslots.timeTypeID == tt.id && getAvailableTimeslots.timeTypeName == tt.name)
						|| (!getAvailableTimeslots.timeTypeID && getAvailableTimeslots.timeTypeName == tt.name)
						|| (!getAvailableTimeslots.timeTypeName && getAvailableTimeslots.timeTypeID == tt.id)) {
							Caretype.values().each() { ct ->
								if ((!getAvailableTimeslots.careTypeID && !getAvailableTimeslots.careTypeName)
								|| (getAvailableTimeslots.careTypeID == ct.id && getAvailableTimeslots.careTypeName == ct.name)
								|| (!getAvailableTimeslots.careTypeID && getAvailableTimeslots.careTypeName == ct.name)
								|| (!getAvailableTimeslots.careTypeName && getAvailableTimeslots.careTypeID == ct.id)) {
									(8..17).each { bookableWorkHour ->
										['00','30'].each { startingMinute ->
											def startTime = Date.parse('yyyyMMddHHmmss', d.format('yyyyMMdd')+bookableWorkHour.toString().padLeft(2,'0')+startingMinute+'00');
											def endTime
											use( TimeCategory ) {
												endTime = startTime + 30.minutes
											}
											resp.timeslotDetail.add(
													new TimeslotType(
													startTimeInclusive: startTime.format('yyyyMMddHHmmss'),
													endTimeExclusive: endTime.format('yyyyMMddHHmmss'),
													healthcareFacility: healthcareFacilityInstance.healthcareFacility,
													performer: p.performerId,
													bookingId: getAvailableTimeslots.bookingId,
													subjectOfCare: getAvailableTimeslots.subjectOfCare,
													healthcareFacilityName: healthcareFacilityInstance.healthcareFacilityName,
													performerName: p.title + ' ' + p.firstName + ' ' + p.lastName,
													timeTypeName: tt.name,
													timeTypeID: tt.id,
													careTypeName: ct.name,
													careTypeID: ct.id,
													cancelBookingAllowed: true,
													rebookingAllowed: true,
													messageAllowed: false)
													)
										}
									}
								}
							}
						}
					}
				}
			}
			
			if (getAvailableTimeslots.performer.empty) {
				log.debug "No performer filter found, fetching all performers"
				Performer.findAll().each buildPerformerDates
			} else {
				log.debug "Performer filter found, fetching filtered performers"
				def performers = []
				getAvailableTimeslots.performer.each { pId ->
					log.debug "Fetching performer $pId"
					def performer = Performer.findByPerformerId(pId)
					if (performer) performers.add(performer)
				}
				performers.each buildPerformerDates
			}
		} else {
			log.debug "No healthcare facility with id $getAvailableTimeslots.healthcareFacility returning empty list of available timeslots"
		}
		return resp;

	}
	

}
