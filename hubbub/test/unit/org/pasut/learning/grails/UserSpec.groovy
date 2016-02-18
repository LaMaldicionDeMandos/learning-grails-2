package org.pasut.learning.grails

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test contraints"() {
        def will = new User(userId: 'William')

        //Esto agrega el metodo validate a la clase, y el resto de las cosas de grails
        mockForConstraintsTests(User, [will])

        def user = new User()

        def valid = user.validate()

        def other = new User(userId: 'william', password: 'william')
        other.validate()

        expect: !valid
        and: 'nullable'.equals user.errors['userId']
        and: 'nullable'.equals user.errors['password']
        and: 'validator'.equals other.errors['password']

        // Etc...
    }
}
