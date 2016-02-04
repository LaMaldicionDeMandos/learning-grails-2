package org.pasut.learning.grails

class Profile {

	static belongsTo = User	//Cascada, si se borra un User, se borra el Profile

	byte[] photo
	String fullName
	String bio
	String homepage
	String email
	String timezone
	String country
	String jabberAddress

	static {
		fullName(nullable: true)
		bio(nullable: true, maxSize: 1000)
		homepage(nullable: true, url: true)
		meail(nullable: true, email: true)
		photo(nullable: true)
		country(nullable: true)
		timezone(nullable: true)
		jabberAddress(nullable: true, email: true)
	}
}
