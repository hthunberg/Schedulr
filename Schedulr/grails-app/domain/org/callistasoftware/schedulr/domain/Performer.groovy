package org.callistasoftware.schedulr.domain

class Performer {

   String firstName
	String lastName
	String title
	String performerId

	static hasMany = [timeSlots: Timeslot]

	static constraints = {
		firstName(blank: false)
		lastName(blank: false)
		performerId(blank:false, unique: true)
	}

	public String toString() {
		"$title, $firstName $lastName, $performerId"
	}
}
