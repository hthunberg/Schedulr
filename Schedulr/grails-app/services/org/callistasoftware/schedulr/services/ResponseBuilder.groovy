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
		TimeslotType timeSlotType
		if (timeslot) {
			timeSlotType = buildTimeslotType(timeslot)
		}
		response.setTimeslotDetail(timeSlotType);
		return response
	}

	private static TimeslotType buildTimeslotType(Timeslot timeslot){
		TimeslotType timeSlotType = new TimeslotType()

		//Timeslot information
		timeSlotType.bookingId = timeslot.id
		timeSlotType.cancelBookingAllowed = timeslot.cancelBookingAllowed
		timeSlotType.careTypeID = timeslot?.caretype?.id
		timeSlotType.careTypeName = timeslot?.caretype?.name
		timeSlotType.endTimeExclusive = timeslot.endTimeExclusive.format("yyyyMMddHHmmss")
		timeSlotType.startTimeInclusive = timeslot.startTimeInclusive.format("yyyyMMddHHmmss")
		timeSlotType.isInvitation = timeslot.isInvitation
		timeSlotType.purpose = timeslot.purpose
		timeSlotType.reason = timeslot.reason
		timeSlotType.rebookingAllowed = timeslot.rebookingAllowed
		timeSlotType.resourceID = timeslot.resourceId
		timeSlotType.resourceName = timeslot.resourceName
		timeSlotType.timeTypeID = timeslot?.timetype?.id
		timeSlotType.timeTypeName = timeslot?.timetype?.name

		//Healtcare facility information
		timeSlotType.healthcareFacility = timeslot.getHealthcareFacility().healthcareFacility
		timeSlotType.healthcareFacilityName = timeslot.getHealthcareFacility().healthcareFacilityName

		//Performer information
		if (timeslot.performer != null) {
			timeSlotType.performer = timeslot.performer.performerId
			timeSlotType.performerName = "$timeslot.performer.firstName $timeslot.performer.lastName, $timeslot.performer.title"
		}
		
		//Subject of care
		timeSlotType.subjectOfCare = timeslot.subjectOfCare.subjectOfCareId

		return timeSlotType

	}
}
