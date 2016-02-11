package org.pasut.learning.grails

class PostController {
    def defaultAction = 'timeline'
    def postService
    def scaffold = true

    def index = {
        if (!params.id) {
            params.id = 'admin'
        }
        redirect(action:'timeline', params:params)
    }

    def timeline = {
        def user = User.findByUserId(params.id)
        render(view: 'timeline', model: [user: user])
    }

    def addPost = {
        try {
            def newPost = postService.createPost(params.id, params.content)
            flash.message = "Added new Post ${newPost.content}."
        } catch(PostException e) {
            flash.message = e.message
        }
        redirect(action: 'timeline', id: params.id)
    }
}
