package org.callistasoftware.schedulr.domain

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
	Caretype caretype
	HealthcareFacility healthcareFacility
	Performer performer

	static belongsTo = [subjectOfCare: SubjectOfCare]

	static mapping = {
		healthcareFacility lazy: false
		performer lazy: false
		subjectOfCare lazy: false
	}

	static constraints = {
		startTimeInclusive(blank: false)
		endTimeExclusive(blank: false)
		caretype(blank:true, nullable: true)
		timetype(blank:true, nullable: true)
		resourceId(blank:true, nullable: true)
		resourceName(blank:true, nullable: true)		
	}

	public String toString() {
		"$startTimeInclusive - $endTimeExclusive"
	}
}
