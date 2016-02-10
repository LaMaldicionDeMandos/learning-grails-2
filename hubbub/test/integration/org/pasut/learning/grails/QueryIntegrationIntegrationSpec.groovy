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

    void "test metodo listOrderBy"() {
        new User(userId: 'glen', password: 'password').save()
        new User(userId: 'peter', password: 'password').save()
        new User(userId: 'cynthia', password: 'sesame').save()
        def users = User.listOrderByUserId()
        expect: ['cynthia', 'glen', 'peter'].equals users*.userId
    }

    void "test metodo countBy"() {
        new User(userId: 'glen', password: 'password').save()
        new User(userId: 'peter', password: 'password').save()
        new User(userId: 'cynthia', password: 'sesame').save()
        def c = User.countByPassword('password')
        expect: 2 == c
    }

    void "test criterias list"() {
        def user = new User(userId: 'glen', password: 'password').save()
        def post = new Post(content: 'Bla', dateCreated: new Date())
        def tag = new Tag(name: 'grails')

        post.addToTags tag
        user.addToPosts post
        user.addToTags tag

        def criteria = Post.createCriteria()
        def posts = criteria.list {
            and {
                eq('user', user)
                between('dateCreated', new Date() - 1, new Date() + 1)
                tags {
                    eq('name', 'grails')
                }
            }
            maxResults(10)
            order('dateCreated', 'desc')
        }

        expect: [post].equals posts
    }

    void "test criterias get"() {
        def user = new User(userId: 'glen', password: 'password').save()
        def post = new Post(content: 'Bla', dateCreated: new Date())
        def tag = new Tag(name: 'grails')

        post.addToTags tag
        user.addToPosts post
        user.addToTags tag

        def criteria = Post.createCriteria()
        def result = criteria.get {
            and {
                eq('user', user)
                between('dateCreated', new Date() - 1, new Date() + 1)
                tags {
                    eq('name', 'grails')
                }
            }
            maxResults(10)
            order('dateCreated', 'desc')
        }

        expect: post.equals result
    }

    void "test criterias count"() {
        def user = new User(userId: 'glen', password: 'password').save()
        def post = new Post(content: 'Bla', dateCreated: new Date())
        def tag = new Tag(name: 'grails')

        post.addToTags tag
        user.addToPosts post
        user.addToTags tag

        def criteria = Post.createCriteria()
        def result = criteria.count {
            and {
                eq('user', user)
                between('dateCreated', new Date() - 1, new Date() + 1)
                tags {
                    eq('name', 'grails')
                }
            }
            maxResults(10)
            order('dateCreated', 'desc')
        }

        expect: 1 == result
    }

    void "test criterias with method"() {
        def user = new User(userId: 'glen', password: 'password').save()
        def post = new Post(content: 'Bla', dateCreated: new Date())
        def tag = new Tag(name: 'grails')

        post.addToTags tag
        user.addToPosts post
        user.addToTags tag

        def result = Post.withCriteria() {
            and {
                eq('user', user)
                between('dateCreated', new Date() - 1, new Date() + 1)
                tags {
                    eq('name', 'grails')
                }
            }
            maxResults(10)
            order('dateCreated', 'desc')
        }

        expect: [post].equals result
    }

    void "test criterias define alias y projection"() {
        def user = new User(userId: 'glen', password: 'password').save()
        def post = new Post(content: 'Bla', dateCreated: new Date())
        def tag = new Tag(name: 'grails')

        post.addToTags tag
        user.addToPosts post
        user.addToTags tag

        def post2 = new Post(content: 'Ble', dateCreated: new Date())
        post2.addToTags tag
        user.addToPosts post2

        def post3 = new Post(content: 'Bli', dateCreated: new Date())
        def tag2 = new Tag(name: 'rails')
        post2.addToTags tag2
        user.addToPosts post3
        user.addToTags tag2

        def result = Post.withCriteria() {
            createAlias('user', 'u')
            createAlias('tags', 't')
            eq('u.userId', 'glen')
            order('t.name')
            projections {
                groupProperty('t.name')
                count("t.id")
            }
        }

        expect: [['grails', 2], ['rails', 1]].equals result

        def tagcloudMap = result.inject([ : ]) { map, _tag -> map[ _tag[0] ] = _tag[1]; map }
    }
}
