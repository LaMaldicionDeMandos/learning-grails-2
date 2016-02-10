package org.pasut.learning.grails

class PostController {
    def scaffold = true

    def timeline = {
        def user = User.findByUserId(params.id)
        [user: user]
    }
}
