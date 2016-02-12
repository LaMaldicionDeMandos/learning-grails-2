class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/timeline/$id"(controller: 'post', action: 'timeline')
        "/user/$id"(controller: 'user', action: 'profile')
        "/"(view:"/index")
        "500"(view:'/errors')
        "404"(view:'/errors')
	}
}
