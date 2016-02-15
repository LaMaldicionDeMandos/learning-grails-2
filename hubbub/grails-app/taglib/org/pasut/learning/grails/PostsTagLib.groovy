package org.pasut.learning.grails

class PostsTagLib {

    static final namespace = 'h'

    def posts = { attrs, body ->
        def posts = attrs.in
        posts?.each { post ->
            out << body(post: post)
        }
    }
}
