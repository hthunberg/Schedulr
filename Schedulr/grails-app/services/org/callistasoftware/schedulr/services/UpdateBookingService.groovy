package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import java.text.ParseException

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.HealthcareFacility
import org.callistasoftware.schedulr.domain.Performer
import org.callistasoftware.schedulr.domain.SubjectOfCare
import org.callistasoftware.schedulr.domain.Timeslot
import org.callistasoftware.schedulr.types.Caretype
import org.callistasoftware.schedulr.types.Timetype
import org.springframework.beans.factory.annotation.Autowired

import riv.interoperability.headers._1.ActorType
import se.riv.crm.scheduling.updatebooking.v1.rivtabp21.UpdateBookingResponderInterface
import se.riv.crm.scheduling.updatebookingresponder.v1.UpdateBookingResponseType
import se.riv.crm.scheduling.updatebookingresponder.v1.UpdateBookingType
import se.riv.crm.scheduling.v1.ResultCodeEnum



@WebService(serviceName = "UpdateBookingResponderService",
endpointInterface = "se.riv.crm.scheduling.updatebooking.v1.rivtabp21.UpdateBookingResponderInterface",
portName = "UpdateBookingPort",
targetNamespace = "urn:riv:crm:scheduling:UpdateBooking:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/UpdateBookingInteraction/UpdateBookingInteraction_1.1_RIVTABP21.wsdl")
class UpdateBookingService implements UpdateBookingResponderInterface {

	@Autowired(required = false)
	EngagementIndexService engagementIndexService
	
	@Override
	public UpdateBookingResponseType updateBooking(String logicalAddress, ActorType actor, UpdateBookingType updateBooking) {
		log.info """
			UpdateBooking
			healthcareFacility: $updateBooking.requestedTimeslot.healthcareFacility
			bookingId: $updateBooking.requestedTimeslot.bookingId
			start: $updateBooking.requestedTimeslot.startTimeInclusive
			end: $updateBooking.requestedTimeslot.startTimeInclusive
			"""

		hasText(updateBooking.requestedTimeslot.startTimeInclusive, "missing argument \"requestedTimeslot.startTimeInclusive\"")
		hasText(updateBooking.requestedTimeslot.endTimeExclusive, "missing argument \"requestedTimeslot.endTimeExclusive\"")
		hasText(updateBooking.requestedTimeslot.healthcareFacility, "missing argument \"requestedTimeslot.healthcare_facility\"")
		hasText(updateBooking.requestedTimeslot.bookingId, "missing argument \"requestedTimeslot.bookingId\"")
		hasText(updateBooking.requestedTimeslot.subjectOfCare, "missing argument \"requestedTimeslot.subject_of_care\"")
		hasText(updateBooking.subjectOfCareInfo.firstName, "missing argument \"subjectOfCareInfo.firstName\"")
		hasText(updateBooking.subjectOfCareInfo.lastName, "missing argument \"subjectOfCareInfo.lastName\"")
		isTrue(logicalAddress == updateBooking.requestedTimeslot.healthcareFacility, '"logicalAddress" differs from "requestedTimeslot.healthcareFacility"')
			
		UpdateBookingResponseType resp = new UpdateBookingResponseType()
		
		def healthcareFacilityInstance = HealthcareFacility.findByHealthcareFacility(updateBooking.requestedTimeslot.healthcareFacility)
		if (!healthcareFacilityInstance) {
			def message = "HealthcareFacility $updateBooking.requestedTimeslot.healthcareFacility not found"
			log.debug message
			resp.resultCode = ResultCodeEnum.ERROR
			resp.resultText = message
			return resp
		}
		Timeslot timeslotInstance
		Timeslot.withTransaction {
			timeslotInstance = Timeslot.findByIdAndHealthcareFacility(updateBooking.requestedTimeslot.bookingId, healthcareFacilityInstance)
			if (!timeslotInstance) {
				def message = "Booking not found with id $updateBooking.requestedTimeslot.bookingId at healthcareFacility $updateBooking.requestedTimeslot.requestedTimeslot.healthcareFacility"
				log.debug message
				resp.resultCode = ResultCodeEnum.ERROR
				resp.resultText = message
				return resp
			}
			
			try {
				timeslotInstance.startTimeInclusive = Date.parse("yyyyMMddHHmmss", updateBooking.requestedTimeslot.startTimeInclusive)
				timeslotInstance.endTimeExclusive = Date.parse("yyyyMMddHHmmss", updateBooking.requestedTimeslot.endTimeExclusive)
			} catch (ParseException e) {
				def message = "Incorrect date format found. Dates shall be formatted yyyyMMddHHmmss start: $updateBooking.requestedTimeslot.startTimeInclusive end: $updateBooking.requestedTimeslot.endTimeExclusive"
				log.debug message, e
				resp.resultCode = ResultCodeEnum.ERROR
				resp.resultText = message
				return resp
			}
			timeslotInstance.healthcareFacility = healthcareFacilityInstance
			if (updateBooking.requestedTimeslot.performer) {
				Performer performer = Performer.findByPerformerId(updateBooking.requestedTimeslot.performer)
				if (!performer) {
					def message = "No such performer, hsa-id: $updateBooking.requestedTimeslot.performer"
					log.debug message
					resp.resultCode = ResultCodeEnum.ERROR
					resp.resultText = message
					return resp
					
				}
				timeslotInstance.performer = performer
			}
			SubjectOfCare subjectOfCare = SubjectOfCare.findBySubjectOfCareId(updateBooking.requestedTimeslot.subjectOfCare)
			if (!subjectOfCare) {
				log.debug "Didn't find subject of care, creating a new one"
				subjectOfCare = new SubjectOfCare()
				subjectOfCare.subjectOfCareId = updateBooking.requestedTimeslot.subjectOfCare
				subjectOfCare.phone = updateBooking.subjectOfCareInfo.phone
				subjectOfCare.email = updateBooking.subjectOfCareInfo.email
				subjectOfCare.homeAddress = updateBooking.subjectOfCareInfo.address
				subjectOfCare.coAddress = updateBooking.subjectOfCareInfo.coaddress
				subjectOfCare.firstName = updateBooking.subjectOfCareInfo.firstName
				subjectOfCare.middleName = updateBooking.subjectOfCareInfo.middleName
				subjectOfCare.lastName = updateBooking.subjectOfCareInfo.lastName
			} else {
				log.debug "Found old subject of care, updating it"
				updateBooking.subjectOfCareInfo.properties.each() {key, value -> println "${key} == ${value}" }
				
				if (updateBooking.subjectOfCareInfo.phone) {
					log.debug "Updating phone: $updateBooking.subjectOfCareInfo.phone"
					subjectOfCare.phone = updateBooking.subjectOfCareInfo.phone
				}
				if (updateBooking.subjectOfCareInfo.email) {
					subjectOfCare.email = updateBooking.subjectOfCareInfo.email
				}
				if (updateBooking.subjectOfCareInfo.address) { 
					subjectOfCare.homeAddress = updateBooking.subjectOfCareInfo.address
				}
				if (updateBooking.subjectOfCareInfo.coaddress) { 
					subjectOfCare.coAddress = updateBooking.subjectOfCareInfo.coaddress
				}
				subjectOfCare.firstName = updateBooking.subjectOfCareInfo.firstName
				if (updateBooking.subjectOfCareInfo.middleName) {
					subjectOfCare.middleName = updateBooking.subjectOfCareInfo.middleName
				}
				subjectOfCare.lastName = updateBooking.subjectOfCareInfo.lastName
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
			
			timeslotInstance.purpose = updateBooking.requestedTimeslot.purpose
			timeslotInstance.reason = updateBooking.requestedTimeslot.reason
			timeslotInstance.resourceName = updateBooking.requestedTimeslot.resourceName
			timeslotInstance.healthcareFacilityNameFromBooking = updateBooking.requestedTimeslot.healthcareFacilityName
			timeslotInstance.performerNameFromBooking = updateBooking.requestedTimeslot.performerName
			timeslotInstance.resourceId = updateBooking.requestedTimeslot.resourceID
			
			if (updateBooking.requestedTimeslot.timeTypeID) {
				Timetype timetype = Timetype.getById(updateBooking.requestedTimeslot.timeTypeID);
				if (!timetype) {
					def message = "No such time type, id: $updateBooking.requestedTimeslot.timeTypeID"
					log.debug message
					resp.resultCode = ResultCodeEnum.ERROR
					resp.resultText = message
					return resp
				}
				timeslotInstance.timetype = timetype;
			}
			timeslotInstance.timeTypeNameFromBooking = updateBooking.requestedTimeslot.timeTypeName
			
			if (updateBooking.requestedTimeslot.careTypeID) {
				Caretype caretype = Caretype.getById(updateBooking.requestedTimeslot.careTypeID);
				if (!caretype) {
					def message = "No such care type, id: $updateBooking.requestedTimeslot.careTypeID"
					log.debug message
					resp.resultCode = ResultCodeEnum.ERROR
					resp.resultText = message
					return resp
				}
				timeslotInstance.caretype = caretype;
			}
			timeslotInstance.careTypeNameFromBooking = updateBooking.requestedTimeslot.careTypeName
			timeslotInstance.notificationFromBooking = updateBooking.notification		
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
		return resp
	}

}
