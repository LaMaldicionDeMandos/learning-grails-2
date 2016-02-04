package org.pasut.learning.grails

class Profile {

	static belongsTo = User	//Cascada, si se borra un User, se borra el Profile

    static constraints = {
    }
}
