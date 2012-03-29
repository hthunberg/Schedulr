package org.callistasoftware.schedulr.services

import org.callistasoftware.schedulr.domain.Timeslot

import se.riv.crm.scheduling.getbookingdetailsresponder.v1.GetBookingDetailsResponseType
import se.riv.crm.scheduling.getsubjectofcarescheduleresponder.v1.GetSubjectOfCareScheduleResponseType
import se.riv.crm.scheduling.v1.TimeslotType

class ResponseBuilder {

	static GetSubjectOfCareScheduleResponseType buildSubjectOfCareSchedule(List timeSlots){
		def response = new GetSubjectOfCareScheduleResponseType()

		timeSlots.each {timeSlot ->
			TimeslotType timeSlotType = buildTimeslotType(timeSlot)
			response.getTimeslotDetail().add(timeSlotType)
		}

		return response
	}

	static GetBookingDetailsResponseType buildBookingDetails(Timeslot timeslot){
		def response = new GetBookingDetailsResponseType()
		TimeslotType timeSlotType = buildTimeslotType(timeslot)
		response.setTimeslotDetail(timeSlotType);
		return response
	}

	private static TimeslotType buildTimeslotType(Timeslot timeslot){
		TimeslotType timeSlotType = new TimeslotType()

		//Timeslot information
		timeSlotType.bookingId = timeslot.id
		timeSlotType.cancelBookingAllowed = timeslot.cancelBookingAllowed
		timeSlotType.careTypeID = timeslot.caretype.id
		timeSlotType.careTypeName = timeslot.caretype.value
		timeSlotType.endTimeExclusive = timeslot.endTimeExclusive.format("yyyyMMddHHmmss")
		timeSlotType.startTimeInclusive = timeslot.startTimeInclusive.format("yyyyMMddHHmmss")
		timeSlotType.isInvitation = timeslot.isInvitation
		timeSlotType.purpose = timeslot.purpose
		timeSlotType.reason = timeslot.reason
		timeSlotType.rebookingAllowed = timeslot.rebookingAllowed
		timeSlotType.resourceID = timeslot.resourcetype.id
		timeSlotType.resourceName = timeslot.resourcetype.value
		timeSlotType.timeTypeID = timeslot.timetype.id
		timeSlotType.timeTypeName = timeslot.timetype.value

		//Healtcare facility information
		timeSlotType.healthcareFacility = timeslot.getHealthcareFacility().healthcareFacility
		timeSlotType.healthcareFacilityName = timeslot.getHealthcareFacility().healthcareFacilityName

		//Performer information
		timeSlotType.performer = timeslot.performer.performerId
		timeSlotType.performerName = timeslot.performer.firstName

		//Subject of care
		timeSlotType.subjectOfCare = timeslot.subjectOfCare.subjectOfCareId

		return timeSlotType

	}
}
