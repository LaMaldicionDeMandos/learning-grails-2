package org.pasut.learning.grails

class PostController {
    def scaffold = true

    def timeline = {
        def user = User.findByUserId(params.id, [sort: 'dateCreated', order: 'desc'])
        render(view: 'timeline', model: [user: user])
    }
}
