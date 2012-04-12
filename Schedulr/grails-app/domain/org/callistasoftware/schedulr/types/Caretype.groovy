package org.callistasoftware.schedulr.types

enum Caretype {
	REHAB("Rehab"),
	SAMPLING("Provtagning"),
	UPVISIT("Återbesök"),
	DOCTOR("Tid hos läkare"),
	OTHER("Övrigt"),

	private final String name


	Caretype(String name){
		this.name = name;
	}

	String toString() {
		name
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
