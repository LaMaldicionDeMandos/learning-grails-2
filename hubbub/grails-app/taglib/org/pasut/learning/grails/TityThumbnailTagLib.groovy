package org.pasut.learning.grails

class TityThumbnailTagLib {

    static final namespace = 'h'

    def tinyThumbnail = { attrs, body ->
        def userId = attrs.userId
        out << "<img src='"
        out << g.createLink(action: "renderImage", controller: "image", id: userId)
        out << "' alt='${userId}'"
    }
}
