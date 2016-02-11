package org.pasut.learning.grails

class PhotoUploadCommand {
    byte[] photo
    String userId
}

class ImageController {
    def upload = {PhotoUploadCommand command ->
        def user = User.findByUserId(command.userId)
        user.profile.photo = command.photo
        redirect(action: 'view', id: command.userId)
    }
    def form = {
        [userList: User.list()]
    }
    def view = {

    }
}
