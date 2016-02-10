package org.pasut.learning.grails

class UserController {
    def scaffold = true

    def search = {

    }

    def results = {
        def users = User.findAllByUserIdLike("%${params.userId}%", [fetch: [posts:'lazy']])
        return [users: users, term: params.userId]
    }
}
