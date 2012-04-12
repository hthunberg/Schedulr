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
