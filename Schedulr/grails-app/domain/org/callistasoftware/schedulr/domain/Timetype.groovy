package org.callistasoftware.schedulr.domain

enum Timetype {
	OPEN("1","Öppen"),
	OTHER("5","Övrigt"),

	private final String value
	private final String id

	Timetype(String id, String value){
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
		[OPEN, OTHER]
	}
}
