package org.callistasoftware.schedulr.domain

enum Resourcetype {
	RESOURCE1("1","Resurs 1"),
	RESOURCE2("2","Resurs 2"),

	private final String value
	private final String id

	Resourcetype(String id, String value){
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
		[RESOURCE1, RESOURCE2]
	}
}
