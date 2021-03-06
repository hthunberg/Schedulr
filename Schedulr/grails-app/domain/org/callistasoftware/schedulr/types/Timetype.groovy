package org.callistasoftware.schedulr.types

enum Timetype {
	OPEN("1","Öppen"),
	OTHER("5","Övrigt")

	final String name
	final String id

	Timetype(String id, String name){
		this.name = name;
		this.id = id;
	}
	
	public static Timetype getById(String id) {
		for(Timetype e : values()) {
			if(e.id == id) return e;
		}
		return null;
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
