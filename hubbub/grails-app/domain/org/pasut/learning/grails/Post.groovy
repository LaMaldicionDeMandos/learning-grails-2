package org.pasut.learning.grails

class Post {
	String content
	Date dateCreated

	static belongsTo = [user: User] // Tengo una referencia al padre

    static constraints = {
    	content(black: false)
    }
}
