package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.HealthcareFacility
import org.callistasoftware.schedulr.domain.Performer
import org.callistasoftware.schedulr.types.Caretype;
import org.callistasoftware.schedulr.types.Timetype;

import riv.interoperability.headers._1.ActorType
import se.riv.crm.scheduling.getavailabledates.v1.rivtabp21.GetAvailableDatesResponderInterface
import se.riv.crm.scheduling.getavailabledatesresponder.v1.GetAvailableDatesResponseType
import se.riv.crm.scheduling.getavailabledatesresponder.v1.GetAvailableDatesType
import se.riv.crm.scheduling.getavailabledatesresponder.v1.PerformerAvailabilityByDateType;
import se.riv.crm.scheduling.v1.PerformerInfoType

@WebService(serviceName = "GetAvailableDatesResponderService",
endpointInterface = "se.riv.crm.scheduling.getavailabledates.v1.rivtabp21.GetAvailableDatesResponderInterface",
portName = "GetAvailableDatesPort",
targetNamespace = "urn:riv:crm:scheduling:GetAvailableDates:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/GetAvailableDatesInteraction/GetAvailableDatesInteraction_1.1_RIVTABP21.wsdl")
class GetAvailableDatesService implements GetAvailableDatesResponderInterface{
	
	@Override
	public GetAvailableDatesResponseType getAvailableDates(String logicalAddress,
			ActorType actor, GetAvailableDatesType getAvailableDates) {
		log.debug "GetAvailableDates"
		hasText(getAvailableDates.healthcareFacility, 'missing argument "healthcareFacility"')
		hasText(getAvailableDates.startDateInclusive, 'missing argument "startDateInclusive"')
		hasText(getAvailableDates.endDateInclusive, 'missing argument "endDateInclusive"')
		hasText(getAvailableDates.subjectOfCare, "missing argument \"subjectOfCare\"")
		isTrue(logicalAddress == getAvailableDates.healthcareFacility, '"logicalAddress" differs from "healthcare_facility"')
		
		GetAvailableDatesResponseType resp = new GetAvailableDatesResponseType();
		def healthcareFacilityInstance = HealthcareFacility.findByHealthcareFacility(getAvailableDates.healthcareFacility)
		if (healthcareFacilityInstance) {
			def start = Date.parse('yyyyMMdd', getAvailableDates.startDateInclusive)
			def end = Date.parse('yyyyMMdd', getAvailableDates.endDateInclusive)
			def buildPerformerDates = { p ->
				(start..end).each() { d ->
					log.debug "${d.format('yyyy-MM-dd')}"
					Timetype.values().each() { tt ->
						if ((!getAvailableDates.timeTypeID && !getAvailableDates.timeTypeName)
						|| (getAvailableDates.timeTypeID == tt.id && getAvailableDates.timeTypeName == tt.name)
						|| (!getAvailableDates.timeTypeID && getAvailableDates.timeTypeName == tt.name)
						|| (!getAvailableDates.timeTypeName && getAvailableDates.timeTypeID == tt.id)) {
							Caretype.values().each() { ct ->
								if ((!getAvailableDates.careTypeID && !getAvailableDates.careTypeName)
								|| (getAvailableDates.careTypeID == ct.id && getAvailableDates.careTypeName == ct.name)
								|| (!getAvailableDates.careTypeID && getAvailableDates.careTypeName == ct.name)
								|| (!getAvailableDates.careTypeName && getAvailableDates.careTypeID == ct.id)) {
									resp.performerAvailabilityByDate.add(
											new PerformerAvailabilityByDateType(
											healthcareFacility: getAvailableDates.healthcareFacility,
											performer: p.performerId,
											date: d.format('yyyyMMdd'),
											timeTypeName: tt.name,
											timeTypeID: tt.id,
											careTypeName: ct.name,
											careTypeID: ct.id)
											)
								}
							}
						}
					}
				}
			}
			
			if (getAvailableDates.performer.empty) {
				log.debug "No performer filter found, fetching all performers"
				Performer.findAll().each buildPerformerDates
			} else {
				log.debug "Performer filter found, fetching filtered performers"
				def performers = []
				getAvailableDates.performer.each { pId ->
					log.debug "Fetching performer $pId"
					def performer = Performer.findByPerformerId(pId)
					if (performer) performers.add(performer)
				}
				performers.each buildPerformerDates
			}
		} else {
			log.debug "No healthcare facility with id $getAvailableDates.healthcareFacility returning empty list of performers"
		}
		return resp;
	}
	

}
