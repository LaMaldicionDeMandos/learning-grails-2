package org.pasut.learning.grails

class Profile {

	static belongsTo = User	

	byte[] photo
	String fullName
	String bio
	String homepage
	String email
	String timezone
	String country
	String jabberAddress

	static constraints = {
		fullName(nullable: true)
		bio(nullable: true, maxSize: 1000)
		homepage(nullable: true, url: true)
		email(nullable: true, email: true)
		photo(nullable: true, maxSize: 200000)
		country(nullable: true)
		timezone(nullable: true)
		jabberAddress(nullable: true, email: true)	
	}

	String toString() {
		"Profile for ${fullName} (${id})"
	}
}
