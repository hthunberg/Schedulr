package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import java.text.ParseException;

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.HealthcareFacility
import org.callistasoftware.schedulr.domain.Performer
import org.callistasoftware.schedulr.domain.SubjectOfCare
import org.callistasoftware.schedulr.domain.Timeslot
import org.callistasoftware.schedulr.types.Caretype
import org.callistasoftware.schedulr.types.Timetype
import org.springframework.beans.factory.annotation.Autowired

import riv.interoperability.headers._1.ActorType
import se.riv.crm.scheduling.makebooking.v1.rivtabp21.MakeBookingResponderInterface
import se.riv.crm.scheduling.makebookingresponder.v1.MakeBookingResponseType
import se.riv.crm.scheduling.makebookingresponder.v1.MakeBookingType
import se.riv.crm.scheduling.v1.ResultCodeEnum



@WebService(serviceName = "MakeBookingResponderService",
endpointInterface = "se.riv.crm.scheduling.makebooking.v1.rivtabp21.MakeBookingResponderInterface",
portName = "MakeBookingPort",
targetNamespace = "urn:riv:crm:scheduling:MakeBooking:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/MakeBookingInteraction/MakeBookingInteraction_1.1_RIVTABP21.wsdl")
class MakeBookingService implements MakeBookingResponderInterface {

	@Autowired(required = false)
	EngagementIndexService engagementIndexService
	
	@Override
	public MakeBookingResponseType makeBooking(String logicalAddress, ActorType actor,
			MakeBookingType makeBooking) {
		log.info """
			MakeBooking
			healthcareFacilityMed: $makeBooking.healthcareFacilityMed
			healthcareFacility: $makeBooking.requestedTimeslot.healthcareFacility
			bookingId: $makeBooking.requestedTimeslot.bookingId
			start: $makeBooking.requestedTimeslot.startTimeInclusive
			end: $makeBooking.requestedTimeslot.startTimeInclusive
			"""

		hasText(makeBooking.healthcareFacilityMed, "missing argument \"healthcare_facility_med\"")
		hasText(makeBooking.requestedTimeslot.startTimeInclusive, "missing argument \"requestedTimeslot.startTimeInclusive\"")
		hasText(makeBooking.requestedTimeslot.endTimeExclusive, "missing argument \"requestedTimeslot.endTimeExclusive\"")
		hasText(makeBooking.requestedTimeslot.healthcareFacility, "missing argument \"requestedTimeslot.healthcare_facility\"")
		hasText(makeBooking.requestedTimeslot.subjectOfCare, "missing argument \"requestedTimeslot.subject_of_care\"")
		hasText(makeBooking.subjectOfCareInfo.firstName, "missing argument \"subjectOfCareInfo.firstName\"")
		hasText(makeBooking.subjectOfCareInfo.lastName, "missing argument \"subjectOfCareInfo.lastName\"")
		isTrue(logicalAddress == makeBooking.requestedTimeslot.healthcareFacility, '"logicalAddress" differs from "requestedTimeslot.healthcareFacility"')
			
		MakeBookingResponseType resp = new MakeBookingResponseType()
		
		def healthcareFacilityInstance = HealthcareFacility.findByHealthcareFacility(makeBooking.requestedTimeslot.healthcareFacility)
		if (!healthcareFacilityInstance) {
			def message = "HealthcareFacility $makeBooking.requestedTimeslot.healthcareFacility not found"
			log.debug message
			resp.resultCode = ResultCodeEnum.ERROR
			resp.resultText = message
			return resp
		}
		Timeslot timeslotInstance
		Timeslot.withTransaction {
			if (makeBooking.requestedTimeslot.bookingId) {
				// If the makeBooking holds a booking id, try to find the booking
				timeslotInstance = Timeslot.findByIdAndHealthcareFacility(makeBooking.requestedTimeslot.bookingId, healthcareFacilityInstance)
				if (!timeslotInstance) {
					def message = "Booking not found with id $makeBooking.requestedTimeslot.bookingId at healthcareFacility $makeBooking.requestedTimeslot.requestedTimeslot.healthcareFacility"
					log.debug message
					resp.resultCode = ResultCodeEnum.ERROR
					resp.resultText = message
					return resp
				}
			} else { 
				// ... else create a new booking
				timeslotInstance = new Timeslot()
			}
			
			def healthcareFacilityMedInstance = HealthcareFacility.findByHealthcareFacility(makeBooking.healthcareFacilityMed)
			if (!healthcareFacilityMedInstance) {
				def message = "HealthcareFacility with medical responsibility $makeBooking.healthcareFacilityMed not found" 
				log.debug message
				resp.resultCode = ResultCodeEnum.ERROR
				resp.resultText = message
				return resp
			}
			timeslotInstance.healthcareFacilityMed = healthcareFacilityMedInstance
			try {
				timeslotInstance.startTimeInclusive = Date.parse("yyyyMMddHHmmss", makeBooking.requestedTimeslot.startTimeInclusive)
				timeslotInstance.endTimeExclusive = Date.parse("yyyyMMddHHmmss", makeBooking.requestedTimeslot.endTimeExclusive)
			} catch (ParseException e) {
				def message = "Incorrect date format found. Dates shall be formatted yyyyMMddHHmmss start: $makeBooking.requestedTimeslot.startTimeInclusive end: $makeBooking.requestedTimeslot.endTimeExclusive"
				log.debug message, e
				resp.resultCode = ResultCodeEnum.ERROR
				resp.resultText = message
				return resp
			}
			timeslotInstance.healthcareFacility = healthcareFacilityInstance
			if (makeBooking.requestedTimeslot.performer) {
				Performer performer = Performer.findByPerformerId(makeBooking.requestedTimeslot.performer)
				if (!performer) {
					def message = "No such performer, hsa-id: $makeBooking.requestedTimeslot.performer"
					log.debug message
					resp.resultCode = ResultCodeEnum.ERROR
					resp.resultText = message
					return resp
					
				}
				timeslotInstance.performer = performer
			}
			//SubjectOfCare.withTransaction{
				SubjectOfCare subjectOfCare = SubjectOfCare.findBySubjectOfCareId(makeBooking.requestedTimeslot.subjectOfCare)
				if (!subjectOfCare) {
					log.debug "Didn't find subject of care, creating a new one"
					subjectOfCare = new SubjectOfCare()
					subjectOfCare.subjectOfCareId = makeBooking.requestedTimeslot.subjectOfCare
					subjectOfCare.phone = makeBooking.subjectOfCareInfo.phone
					subjectOfCare.email = makeBooking.subjectOfCareInfo.email
					subjectOfCare.homeAddress = makeBooking.subjectOfCareInfo.address
					subjectOfCare.coAddress = makeBooking.subjectOfCareInfo.coaddress
					subjectOfCare.firstName = makeBooking.subjectOfCareInfo.firstName
					subjectOfCare.middleName = makeBooking.subjectOfCareInfo.middleName
					subjectOfCare.lastName = makeBooking.subjectOfCareInfo.lastName
				} else {
					log.debug "Found old subject of care, updating it"
					makeBooking.subjectOfCareInfo.properties.each() {key, value -> println "${key} == ${value}" }
					
					if (makeBooking.subjectOfCareInfo.phone) {
						log.debug "Updating phone: $makeBooking.subjectOfCareInfo.phone"
						subjectOfCare.phone = makeBooking.subjectOfCareInfo.phone
					}
					if (makeBooking.subjectOfCareInfo.email) {
						subjectOfCare.email = makeBooking.subjectOfCareInfo.email
					}
					if (makeBooking.subjectOfCareInfo.address) { 
						subjectOfCare.homeAddress = makeBooking.subjectOfCareInfo.address
					}
					if (makeBooking.subjectOfCareInfo.coaddress) { 
						subjectOfCare.coAddress = makeBooking.subjectOfCareInfo.coaddress
					}
					subjectOfCare.firstName = makeBooking.subjectOfCareInfo.firstName
					if (makeBooking.subjectOfCareInfo.middleName) {
						subjectOfCare.middleName = makeBooking.subjectOfCareInfo.middleName
					}
					subjectOfCare.lastName = makeBooking.subjectOfCareInfo.lastName
				}
				if (!subjectOfCare.save(flush: true)) {
					def message = "Problem while saving/updating subject of care info"
					log.debug message
					subjectOfCare.properties.each() {key, value -> println "${key} == ${value}" }
					subjectOfCare.errors.allErrors.each {
						log.debug it
					}
					resp.resultCode = ResultCodeEnum.ERROR
					resp.resultText = message
					return resp
				}
				timeslotInstance.subjectOfCare = subjectOfCare
			//}
			
			timeslotInstance.purpose = makeBooking.requestedTimeslot.purpose
			timeslotInstance.reason = makeBooking.requestedTimeslot.reason
			timeslotInstance.resourceName = makeBooking.requestedTimeslot.resourceName
			timeslotInstance.healthcareFacilityNameFromBooking = makeBooking.requestedTimeslot.healthcareFacilityName
			timeslotInstance.performerNameFromBooking = makeBooking.requestedTimeslot.performerName
			timeslotInstance.resourceId = makeBooking.requestedTimeslot.resourceID
			
			if (makeBooking.requestedTimeslot.timeTypeID) {
				Timetype timetype = Timetype.getById(makeBooking.requestedTimeslot.timeTypeID);
				if (!timetype) {
					def message = "No such time type, id: $makeBooking.requestedTimeslot.timeTypeID"
					log.debug message
					resp.resultCode = ResultCodeEnum.ERROR
					resp.resultText = message
					return resp
				}
				timeslotInstance.timetype = timetype;
			}
			timeslotInstance.timeTypeNameFromBooking = makeBooking.requestedTimeslot.timeTypeName
			
			if (makeBooking.requestedTimeslot.careTypeID) {
				Caretype caretype = Caretype.getById(makeBooking.requestedTimeslot.careTypeID);
				if (!caretype) {
					def message = "No such care type, id: $makeBooking.requestedTimeslot.careTypeID"
					log.debug message
					resp.resultCode = ResultCodeEnum.ERROR
					resp.resultText = message
					return resp
				}
				timeslotInstance.caretype = caretype;
			}
			timeslotInstance.careTypeNameFromBooking = makeBooking.requestedTimeslot.careTypeName
			timeslotInstance.notificationFromBooking = makeBooking.notification		
			if (!timeslotInstance.save(flush: true)) {
				def message = "Error saving timeslot"
				log.debug message
				
				timeslotInstance.properties.each() {key, value -> println "${key} == ${value}" }
				timeslotInstance.errors.allErrors.each {
					log.debug it
				}
				resp.resultCode = ResultCodeEnum.ERROR
				resp.resultText = message
				return resp
			}
			engagementIndexService.updateEngagementIndex(timeslotInstance)
		}	
		resp.resultCode = ResultCodeEnum.OK
		resp.bookingId = timeslotInstance.id
		return resp
	}

}
