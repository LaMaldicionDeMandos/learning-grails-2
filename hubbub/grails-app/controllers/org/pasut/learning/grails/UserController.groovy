package org.pasut.learning.grails

class UserController {
    def scaffold = true

    def search = {

    }

    def newUser = {}

    def results = {
        def users = User.findAllByUserIdLike("%${params.userId}%", [fetch: [posts:'lazy']])
        return [users: users, term: params.userId]
    }

    def register = {
        def user = new User(params)
        if(user.validate()) {
            user.save()
            flash.message = "Succefully Created User"
            redirect(controller: 'post', action: 'timeline', id: user.userId)
        } else {
            flash.message = "Error registerin user"
            return [user: user]
        }
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
