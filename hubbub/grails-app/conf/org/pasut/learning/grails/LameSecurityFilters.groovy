package org.pasut.learning.grails

class LameSecurityFilters {

    def filters = {
        //secureActions es un nombre que le pongo, puede ser cualquier cosa
        secureActions(controller:'post', action:'(addPost|deletePost)') {
            before = {
                if (params.logonId) {
                    session.user = User.findByUserId(params.logonId)
                }
                if (!session.user) {
                    redirect(controller: 'login', action: 'form')
                    return false
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->
                log.debug "Finished running ${controllerName} - ${actionName}"
            }
        }
    }
}
