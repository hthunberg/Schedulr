package org.callistasoftware.schedulr.types

enum Caretype {
	REHAB("1","Rehab"),
	SAMPLING("2","Provtagning"),
	UPVISIT("3","Återbesök"),
	DOCTOR("4","Tid hos läkare"),
	OTHER("5","Övrigt"),

	private final String name
	private final String id

	Caretype(String id, String name){
		this.name = name;
		this.id = id;
	}

	String toString() {
		name
	}

	String getKey() {
		name()
	}
	
	public static Caretype getById(String id) {
		for(Caretype e : values()) {
			if(e.id == id) return e;
		}
		return null;
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
