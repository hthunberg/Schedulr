package org.callistasoftware.schedulr.domain

class Timeslot {

	Date startTimeInclusive
	Date endTimeExclusive
	String purpose
	String reason
	boolean cancelBookingAllowed
	boolean rebookingAllowed
	boolean isInvitation

	Resourcetype resourcetype
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
		caretype(blank:false)
		timetype(blank:false)
		resourcetype(blank:true)
	}

	public String toString() {
		"$startTimeInclusive - $endTimeExclusive"
	}
}
