class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
				controller(matches:/.*[^(ws)].*/)
			}
		}

		"/"(view:"/index")
		"/about"(view:"/about")
		"500"(view:'/error')
	}
}
