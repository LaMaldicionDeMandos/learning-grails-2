package org.pasut.learning.grails

import grails.test.spock.IntegrationSpec

class UserIntegrationIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "when save an user should save in the database"() {
    	def user = new User(userId: 'Marcelo1', password: 'passss')
    	expect: user.save()
    	and: user.id
    	def saved = User.get(user.id)
    	and: 'Marcelo1'.equals saved.userId
    }

    void "when modify an user should save in the database"() {
    	def user = new User(userId: 'Marcelo2', password: 'passss')
    	user.save()
    	def saved = User.get(user.id)
    	saved.password = 'blabla'
    	saved.save()
    	def modif = User.get(user.id)
    	expect: 'blabla'.equals modif.password
    }

    void "when delete an user should delete in the database"() {
    	def user = new User(userId: 'Marcelo3', password: 'passss')
    	expect: user.save()
    	and: user.id
    	def saved = User.get(user.id)
    	saved.delete(flush: true)
    	and: !User.exists(user.id)
    }

    void "should validate constraints"() {
        def user = new User(userId: 'Marcelo3', password: 'pass', homepage: 'nada')
        expect: !user.validate()
        and: user.hasErrors()
        def errors = user.errors
        and: 'size.toosmall'.equals errors.getFieldError('password').code
        and: 'pass'.equals errors.getFieldError('password').rejectedValue
        and: 'url.invalid'.equals errors.getFieldError('homepage').code
        and: 'nada'.equals errors.getFieldError('homepage').rejectedValue
    }

        void "should validate password is not equals than userId"() {
        def user = new User(userId: 'Marcelo4', password: 'Marcelo4')
        expect: !user.validate()
        and: user.hasErrors()
        def errors = user.errors
        and: 'validator.invalid'.equals errors.getFieldError('password').code
    }
}
