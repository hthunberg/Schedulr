package org.callistasoftware.schedulr.domain

import org.callistasoftware.schedulr.types.Caretype;
import org.callistasoftware.schedulr.types.Timetype;

class Timeslot {

	Date startTimeInclusive
	Date endTimeExclusive
	String purpose
	String reason
	boolean cancelBookingAllowed
	boolean rebookingAllowed
	boolean isInvitation

	String resourceId
	String resourceName
	Timetype timetype
	/**
	 * The name of the time type as given by the MakeBooking or UpdateBooking request during booking
	 */
	String timeTypeNameFromBooking
	Caretype caretype
	/**
	 * The name of the care type as given by the MakeBooking or UpdateBooking request during booking
	 */
	String careTypeNameFromBooking
	/**
	 * The booked health care facility (SV: mottagning)
	 */
	HealthcareFacility healthcareFacility
	/**
	 * The name of the healthcare facility as given by the MakeBooking or UpdateBooking request during booking
	 */
	String healthcareFacilityNameFromBooking
	/**
	 * The healthcare facility holding the medical responsibility 
	 * (SV: Den vårdenhet som har det medicinska ansvaret; klinik/vårdcentral/sektion)
	 */
	HealthcareFacility healthcareFacilityMed
	/**
	 * The healthcare professional scheduled for this booking
	 */
	Performer performer
	/**
	 * The name of the performer as given by the MakeBooking or UpdateBooking request during booking
	 */
	String performerNameFromBooking
	/**
	 * Notification as given by the MakeBooking or UpdateBooking request during booking
	 */
	String notificationFromBooking

	static belongsTo = [subjectOfCare: SubjectOfCare]

	static mapping = {
		healthcareFacility lazy: false
		performer lazy: false
		subjectOfCare lazy: false
	}

	static constraints = {
		startTimeInclusive(blank: false)
		endTimeExclusive(blank: false)
		caretype(nullable: true)
		timetype(nullable: true)
		performer(nullable: true)
		resourceId(blank:true, nullable: true)
		resourceName(blank:true, nullable: true)
		
		timeTypeNameFromBooking(blank:true, nullable: true)
		careTypeNameFromBooking(blank:true, nullable: true)
		healthcareFacilityNameFromBooking(blank:true, nullable: true)
		performerNameFromBooking(blank:true, nullable: true)
		notificationFromBooking(blank:true, nullable: true)
		purpose(blank:true, nullable: true)
		reason(blank:true, nullable: true)

		resourceName validator: { resourceName,obj  ->
			if(resourceName != null && obj.resourceId == null) return ['resourceName.withoutResourceId']
		}

		resourceId validator: { resourceId,obj  ->
			if(resourceId != null && obj.resourceName == null) return ['resourceId.withoutResourceName']
		}
	}

	public String toString() {
		"$startTimeInclusive - $endTimeExclusive"
	}
}
