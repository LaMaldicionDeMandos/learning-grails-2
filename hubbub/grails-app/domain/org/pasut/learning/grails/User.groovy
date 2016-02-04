package org.pasut.learning.grails

class User {
	String userId
	String password
	String homepage
	Date dateCreated
	Profile profile

	static constraints = {
		userId(size: 3..20, unique: true)
		password(size: 6..8, validator: {pass, user -> pass != user.userId})
		homepage(url: true, nullable: true)
		dateCreated()
		profile(nullable: true)
	}

	static mapping = {
		profile lazy: false
	}
}
