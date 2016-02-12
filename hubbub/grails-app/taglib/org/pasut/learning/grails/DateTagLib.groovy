package org.pasut.learning.grails

class DateTagLib {
    static final namespace = 'h'
    def dateFromNow = { attrs ->
        def date = attrs.date
        def niceDate = "Ahora!!"

        out << niceDate
    }
}
