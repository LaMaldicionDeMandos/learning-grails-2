package org.pasut.learning.grails

import grails.test.spock.IntegrationSpec

class PostIntegrationIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "should add a post to user"() {
    	def user = new User(userId: 'Marcelo1', password: 'passss').save()
    	def post1 = new Post(content:'First post')
    	user.addToPosts post1
    	def post2 = new Post(content:'Second post')
    	user.addToPosts post2
    	expect: 2 == User.get(user.id).posts.size()
    }
}
