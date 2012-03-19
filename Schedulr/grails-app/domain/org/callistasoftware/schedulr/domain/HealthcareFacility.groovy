package org.callistasoftware.schedulr.domain

/**
 * Represents a healtcare facility, in swedish e.g a VŒrdcentral.
 * 
 */
class HealthcareFacility {
	String healthcareFacility
	String healthcareFacilityName

	static hasMany = [timeSlots: Timeslot]

	static constraints = {
		healthcareFacility(blank: false)
		healthcareFacilityName(blank: false)
	}

	public String toString() {
		"$healthcareFacilityName, $healthcareFacility"
	}
}
