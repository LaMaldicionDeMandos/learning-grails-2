package org.pasut.learning.grails

class Tag {
	String name
	User user
    
    static constraints = {
    	name(black: false)
    }

    static hasMany = [posts: Post]

    static belongsTo = [User, Post] //Esto dice que User y Post tienen el metodo addToTags.
}
