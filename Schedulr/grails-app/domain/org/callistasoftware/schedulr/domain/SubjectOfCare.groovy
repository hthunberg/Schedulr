package org.callistasoftware.schedulr.domain

class SubjectOfCare {

	String subjectOfCareId
	String phone
	String email
	String homeAddress
	String coAddress
	String firstName
	String middleName
	String lastName

	static hasMany = [timeSlots: Timeslot]

	static constraints = {
		firstName(blank: false)
		lastName(blank: false)
		phone(blank: false)
		email(email: true, blank: false, )
		subjectOfCareId(blank: false, unique:true, matches: '([0-9]{8})-([0-9]{4})')
		coAddress(nullable: true)
		homeAddress(blank: false)
		middleName(nullable: true)
	}

	public String toString() {
		"$firstName $middleName $lastName, $subjectOfCareId"
	}
}
