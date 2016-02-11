package org.pasut.learning.grails

import grails.transaction.Transactional

class PostException extends RuntimeException {
    String message
    Post post
}

@Transactional
class PostService {

    Post createPost(String userId, String content) {
        def user = User.findByUserId(userId, [fetch: [posts: 'eager']])
        if (user) {
            Post post = new Post(content:content)
            post.user = user
            post.save()
            user.addToPosts(post)
            if (user.save()) {
                return post
            } else {
                throw new PostException(message: "Invalid or empty post", post:post)
            }
        }
        throw new PostException(message: "Invalid User Id")
    }
}
