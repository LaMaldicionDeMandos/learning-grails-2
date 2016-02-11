package org.pasut.learning.grails

class UserRegistrationCommand {
    String userId
    String password
    String passwordRepeat
    byte[] photo
    String fullName
    String bio
    String homepage
    String email
    String timezone
    String country
    String jabberAddress

    static constraints = {
        userId(size: 3..20)
        password(size: 6..8, blank: false, validator: { pass, urc -> pass.equals(urc.userId) })
        passwordRepeat(nullable: false, validator: { pass2, urc -> pass2.equals(urc.password)})
        fullName(nullable: true)
        bio(nullable: true, maxSize: 1000)
        homepage(nullable: true, url: true)
        email(nullable: true, email: true)
        photo(nullable: true)
        country(nullable: true)
        timezone(nullable: true)
        jabberAddress(email: true, nullable: true)
    }
}

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
            return render(view:'newUser', model: [user:user])
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
