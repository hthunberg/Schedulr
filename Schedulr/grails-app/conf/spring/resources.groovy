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
	
	getAllHealthcareFacilitiesService(org.callistasoftware.schedulr.services.GetAllHealthcareFacilitiesService){
	}

	getAllPerformersService(org.callistasoftware.schedulr.services.GetAllPerformersService){
	}
	
	getAvailableDatesService(org.callistasoftware.schedulr.services.GetAvailableDatesService){
	}
	
	getAvailableTimeslotsService(org.callistasoftware.schedulr.services.GetAvailableTimeslotsService){
	}
	
	makeBookingService(org.callistasoftware.schedulr.services.MakeBookingService){
	}
	
	updateBookingService(org.callistasoftware.schedulr.services.UpdateBookingService){
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
