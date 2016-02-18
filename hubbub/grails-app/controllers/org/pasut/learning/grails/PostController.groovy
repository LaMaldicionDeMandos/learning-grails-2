package org.pasut.learning.grails

class PostController {
    static navigation = [
            [group: 'tabs', action: 'timeline', title: 'My Timeline', order: 0],
            [action: 'home', title: 'Global Timeline', order: 1]
    ]

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

    def addAjaxPost = {
        try {
            def user = User.findByUserId(params.userId)
            log.debug "Creating a new Post: ${params.content} to user: ${params.userId}"
            def newPost = postService.createPost(params.userId, params.content)
            flash.message = "Added new Post ${newPost.content}."
            def recentPosts = Post.findAllByUser(user, [sort: 'dateCreated', order: 'desc', max: 20])
            render { div('Updated') }
        } catch(PostException e) {
            flash.message = e.message
            render {div(class:"errors", e.message)}
        }
    }

    def addAjaxPostWithJson = {
        try {
            def user = User.findByUserId(params.userId)
            log.debug "Creating a new Post: ${params.content} to user: ${params.userId}"
            def newPost = postService.createPost(params.userId, params.content)
            flash.message = "Added new Post ${newPost.content}."
            def recentPosts = Post.findAllByUser(user, [sort: 'dateCreated', order: 'desc', max: 20])
            render(contentType: 'application/json') {
                recentPosts
            }
        } catch(PostException e) {
            flash.message = e.message
            render {div(class:"errors", e.message)}
        }
    }
}
