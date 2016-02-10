package org.pasut.learning.grails

class UserController {
    def scaffold = true

    def search = {

    }

    def results = {
        def users = User.findAllByUserIdLike("%${params.userId}%", [fetch: [posts:'lazy']])
        return [users: users, term: params.userId]
    }

    def advResults = {
        def profileProps = Profile.metaClass.properties*.name
        def profiles = Profile.withCriteria {
            "${params.queryType}" { // Esto es para ver si aplico and o or, en el formulario tengo un field que me dice esto
                params.each { field, value ->
                    if (profileProps.grep(field) && value) {
                        ilike(field, value)
                    }

                }
            }

        }
        [profiles: profiles]
    }
}
