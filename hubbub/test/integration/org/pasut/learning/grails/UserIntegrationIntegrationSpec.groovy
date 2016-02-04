package org.pasut.learning.grails

import grails.test.spock.IntegrationSpec

class UserIntegrationIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    def "test first save ever"() {
    	def user = new User(userId: 'Marcelo', password: 'bla', homepage: 'ble')
    	expect: user.save() 
    	and: user.id
    	def saved = User.get(user.id) 
    	and: 'Marcelo'.equals saved.userId
    }

    def "should delete a user"() {
    	def user = new User(userId: 'Marcelo', password: 'bla', homepage: 'ble')
    	expect: user.save() 
    	and: user.id
    	def saved = User.get(user.id)
    	saved.delete()
    	and: User.exists(user.id)
    }
}
