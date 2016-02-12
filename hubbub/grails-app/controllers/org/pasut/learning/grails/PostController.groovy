package org.pasut.learning.grails

class PostController {
    def defaultAction = 'timeline'
    PostService postService
    def scaffold = true

    def index = {
        if (!params.id) {
            params.id = 'admin'
        }
        redirect(action:'timeline', params:params)
    }

    def home = {
        def posts = Post.list(params)
        def size = Post.count()
        [posts: posts, postsSize: size]
    }

    def timeline = {
        def user = User.findByUserId(params.id)
        render(view: 'timeline', model: [user: user])
    }

    def addPost = {
        try {
            log.debug "Creating a new Post: ${params.content} to user: ${params.id}"
            def newPost = postService.createPost(params.id, params.content)
            flash.message = "Added new Post ${newPost.content}."
        } catch(PostException e) {
            flash.message = e.message
        }
        redirect(action: 'timeline', id: params.id)
    }
}
