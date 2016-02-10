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
}
