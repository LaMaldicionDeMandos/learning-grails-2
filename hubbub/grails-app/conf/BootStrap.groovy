import grails.util.Environment
import org.pasut.learning.grails.Profile
import org.pasut.learning.grails.User

class BootStrap {

    def init = { servletContext ->
        if (Environment.current.equals(Environment.DEVELOPMENT)) {
            populateAdminUser()
        }
    }
    def destroy = {
    }

    void populateAdminUser() {
        if(!User.findByUserId('admin')) {
            def profile = new Profile(email: 'admin@hubbub.com')
            new User(userId: 'admin', password: 'secret', profile: profile).save()
        }
    }
}
