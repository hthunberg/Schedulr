package org.callistasoftware.schedulr.domain

enum Caretype {
	REHAB("1","Rehab"),
	SAMPLING("2","Provtagning"),
	UPVISIT("3","Återbesök"),
	DOCTOR("4","Tid hos läkare"),
	OTHER("5","Övrigt"),

	private final String value
	private final String id

	Caretype(String id, String value){
		this.value = value;
		this.id = id;
	}

	String toString() {
		value
	}

	String getKey() {
		name()
	}

	static list(){
		[
			REHAB,
			SAMPLING,
			UPVISIT,
			DOCTOR,
			OTHER
		]
	}
}
