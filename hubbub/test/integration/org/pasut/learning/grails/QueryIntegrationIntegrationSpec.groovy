package org.pasut.learning.grails

import grails.test.spock.IntegrationSpec

class QueryIntegrationIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test basic dynamic finders"() {
        new User(userId: 'glen', password: 'secret',
                profile: new Profile(email: 'glen@glensmith.com')).save()
        new User(userId: 'peter', password: 'sesame',
                profile: new Profile(homepage: 'http://www.peter.com/')).save()
        def user = User.findByPassword('sesame')
        def user2 = User.findByUserIdAndPassword('glen', 'secret')
        expect: 'peter'.equals user.userId
        and: 'glen'.equals user2.userId
        def now = new Date()
        def users = User.findAllByDateCreatedBetween(now-1, now)
        and: 2.equals users.size()
        def profiles = Profile.findAllByEmailIsNotNull()
        and: 1.equals profiles.size()
    }

    void "test query by example"() {
        new User(userId: 'glen', password: 'password').save()
        new User(userId: 'peter', password: 'password').save()
        new User(userId: 'cynthia', password: 'sesame').save()
        def userToFind1 = new User(userId: 'glen')
        def userToFind2 = new User(userId: 'cynthia')
        def userToFind3 = new User(password: 'password')
        def u1 = User.find(userToFind1)
        def u2 = User.find(userToFind2)
        def u3 = User.findAll(userToFind3)
        expect: 'password'.equals u1.password
        and: 'cynthia'.equals u2.userId
        and: ['glen', 'peter'].equals u3*.userId
    }

    void "test los atributos que se le puede pasar a list"() {
        new User(userId: 'glen', password: 'password').save()
        new User(userId: 'peter', password: 'password').save()
        new User(userId: 'cynthia', password: 'sesame').save()
        def users = User.list([sort: 'userId', order: 'asc', max: 2, fetch: [posts: 'eager']])
        expect: ['cynthia', 'glen'].equals users*.userId
    }
}
