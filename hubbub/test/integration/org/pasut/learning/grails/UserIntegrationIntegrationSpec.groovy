package org.pasut.learning.grails

import grails.test.spock.IntegrationSpec

class UserIntegrationIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "when save an user should save in the database"() {
    	def user = new User(userId: 'Marcelo', password: 'pass', homepage: 'bla')
    	expect: user.save()
    	and: user.id
    	def saved = User.get(user.id)
    	and: 'Marcelo'.equals saved.userId
    }

    void "when modify an user should save in the database"() {
    	def user = new User(userId: 'Marcelo', password: 'pass', homepage: 'bla')
    	user.save()
    	def saved = User.get(user.id)
    	saved.password = 'blabla'
    	saved.save()
    	def modif = User.get(user.id)
    	expect: 'blabla'.equals modif.password
    }

    void "when delete an user should delete in the database"() {
    	def user = new User(userId: 'Marcelo', password: 'pass', homepage: 'bla')
    	expect: user.save()
    	and: user.id
    	def saved = User.get(user.id)
    	saved.delete(flush: true)
    	and: !User.exists(user.id)
    }
}
