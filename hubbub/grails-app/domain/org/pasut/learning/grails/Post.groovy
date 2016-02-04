package org.pasut.learning.grails

class Post {
	String content
	Date dateCreated

	static belongsTo = [user: User] // Tengo una referencia al padre, cuando un User es borrado, todos los Post son borrados

    static constraints = {
    	content(black: false)
    }

    static mappings = {
    	sort dateCreated:'desc'
    }
}
