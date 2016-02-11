package org.pasut.learning.grails

class PhotoUploadCommand {
    byte[] photo
    String userId
}

class ImageController {
    def index = {
        redirect(action: 'form')
    }
    def upload = {PhotoUploadCommand command ->
        def user = User.findByUserId(command.userId)
        user.profile.photo = command.photo
        redirect(action: 'view', id: command.userId)
    }

    def rawUpload = {
        def req = request.getFile('photo')
        req.transferTo(new File('/hubbub/images/${params.userId}/photo.jpg'))
    }
    def form = {
        [userList: User.list()]
    }
    def view = {

    }
}
