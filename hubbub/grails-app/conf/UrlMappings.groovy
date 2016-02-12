class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/profile2/$userId"(controller: 'user', action: 'profile2')
        "/timeline/$id"(controller: 'post', action: 'timeline')
        "/newUser"(controller: 'user', action: 'newUser')
        "/"(view:"/index")
        "500"(view:'/errors')
        "404"(view:'/errors')
	}
}
