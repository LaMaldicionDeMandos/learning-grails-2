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
    }
}
