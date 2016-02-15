package org.pasut.learning.grails

class LameBrowserTagLib {
    static final namespace = 'h'

    def lameBrowser = { attrs, body ->
        if (request.getHeader('User-Agent') =~ attrs.userAgent) {
            out << body()
        }
    }
}
