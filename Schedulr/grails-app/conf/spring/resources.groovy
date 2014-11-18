// Place your Spring DSL code here
beans = {

	//Load CXF modules from cxf.jar
	importBeans('classpath:META-INF/cxf/cxf.xml')

	//Web service implementations
	getSubjectOfCareScheduleService(org.callistasoftware.schedulr.services.GetSubjectOfCareScheduleService) {
	}

	getBookingDetailsService(org.callistasoftware.schedulr.services.GetBookingDetailsService){
	}

	cancelBookingService(org.callistasoftware.schedulr.services.CancelBookingService){
	}
	
	getAllCareTypesService(org.callistasoftware.schedulr.services.GetAllCareTypesService){
	}
	
	getAllTimeTypesService(org.callistasoftware.schedulr.services.GetAllTimeTypesService){
	}

	makeBookingService(org.callistasoftware.schedulr.services.MakeBookingService){
	}
	
	pingForConfigurationService(org.callistasoftware.schedulr.services.PingForConfigurationService){
	}

	switch (grails.util.GrailsUtil.environment) {

		case "production":
			println "Creating production env Spring beans"

			break

		case "test":
			println "Creating test env Spring beans"

			break

		case "development":
			println "Creating development env Spring beans"

			break
	}
}
