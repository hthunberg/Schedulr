package org.callistasoftware.schedulr.services

import se.riv.crm.scheduling.getsubjectofcarescheduleresponder.v1.GetSubjectOfCareScheduleResponseType
import se.riv.crm.scheduling.v1.TimeslotType

class ScheduleBuilder {
	static GetSubjectOfCareScheduleResponseType buildSchedule(List timeSlots){
		def response = new GetSubjectOfCareScheduleResponseType()

		timeSlots.each {timeSlot ->
			TimeslotType timeSlotType = new TimeslotType()

			//Timeslot information
			timeSlotType.bookingId = timeSlot.id
			timeSlotType.cancelBookingAllowed = timeSlot.cancelBookingAllowed
			timeSlotType.careTypeID = timeSlot.caretype.id
			timeSlotType.careTypeName = timeSlot.caretype.value
			timeSlotType.endTimeExclusive = timeSlot.endTimeExclusive.format("yyyyMMddhhmmss")
			timeSlotType.startTimeInclusive = timeSlot.startTimeInclusive.format("yyyyMMddhhmmss")
			timeSlotType.isInvitation = timeSlot.isInvitation
			timeSlotType.purpose = timeSlot.purpose
			timeSlotType.reason = timeSlot.reason
			timeSlotType.rebookingAllowed = timeSlot.rebookingAllowed
			timeSlotType.resourceID = timeSlot.resourcetype.id
			timeSlotType.resourceName = timeSlot.resourcetype.value
			timeSlotType.timeTypeID = timeSlot.timetype.id
			timeSlotType.timeTypeName = timeSlot.timetype.value

			//Healtcare facility information
			timeSlotType.healthcareFacility = timeSlot.getHealthcareFacility().healthcareFacility
			timeSlotType.healthcareFacilityName = timeSlot.getHealthcareFacility().healthcareFacilityName

			//Performer information
			timeSlotType.performer = timeSlot.performer.performerId
			timeSlotType.performerName = timeSlot.performer.firstName

			//Subject of care
			timeSlotType.subjectOfCare = timeSlot.subjectOfCare.subjectOfCareId

			response.getTimeslotDetail().add(timeSlotType)
		}

		return response
	}
}
