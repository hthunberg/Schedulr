package org.callistasoftware.schedulr.types

enum Timetype {
	OPEN("Öppen"),
	OTHER("Övrigt"),

	private final String name

	Timetype(String name){
		this.name = name;
	}

	String toString() {
		name
	}

	String getKey() {
		name()
	}

	static list(){
		[OPEN, OTHER]
	}
}
