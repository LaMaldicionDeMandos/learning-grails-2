package org.pasut.learning.grails

class PostController {
    def scaffold = true

    def timeline = {
        def user = User.findByUserId(params.id)
        render(view: 'timeline', model: [user: user])
    }

    def addPost = {
        def user = User.findByUserId(params.id, [fetch: [posts: 'eager']])
        if (user) {
            Post post = new Post(params)
            post.user = user
            post.save()
            user.addToPosts(post)
            if (user.save()) {
                flash.message = 'Succefully created post'
            } else {
                user.discard()
                flash.message = 'Invalid or empty post'
            }
        } else {
            flash.message = 'Invalid User id'
        }
        redirect(action: 'timeline', id: params.id)
    }
}
