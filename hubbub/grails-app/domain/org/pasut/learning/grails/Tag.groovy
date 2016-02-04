package org.pasut.learning.grails

class Tag {
	String name
	User user
    
    static constraints = {
    	name(black: false)
    }

    static hasMany = [posts: Post]

    static belongsTo = [User, Post] //La tag depende de los usuarios y los Post
}
