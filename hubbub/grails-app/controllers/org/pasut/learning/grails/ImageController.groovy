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
        user.profile.save()
        user.save([flush: true])
        redirect(controller: 'user', action: 'profile', id: command.userId)
    }

    def renderImage = {
        def user = User.findByUserId(params.id)
        if (user?.profile?.photo) {
            response.setContentLength(user.profile.photo.length)
            response.outputStream.write(user.profile.photo)
        } else {
            response.sendError(404)
        }
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
