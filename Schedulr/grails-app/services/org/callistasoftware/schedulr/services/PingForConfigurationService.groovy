package org.callistasoftware.schedulr.services

import grails.util.GrailsUtil

import java.text.SimpleDateFormat

import javax.jws.WebService

import org.codehaus.groovy.grails.commons.ApplicationHolder

import se.riv.itintegration.monitoring.rivtabp21.v1.PingForConfigurationResponderInterface
import se.riv.itintegration.monitoring.v1.ConfigurationType
import se.riv.itintegration.monitoring.v1.PingForConfigurationResponseType
import se.riv.itintegration.monitoring.v1.PingForConfigurationType



@WebService(serviceName = "PingForConfigurationResponderService",
endpointInterface = "se.riv.itintegration.monitoring.rivtabp21.v1.PingForConfigurationResponderInterface",
portName = "PingForConfigurationPort",
targetNamespace = "urn:riv:itintegration:monitoring:PingForConfiguration:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/PingForConfigurationInteraction/PingForConfigurationInteraction_1.0_RIVTABP21.wsdl")
class PingForConfigurationService implements PingForConfigurationResponderInterface{


	/**
	 * Ping for configuration is used for monitoring the application
	 */

	public PingForConfigurationResponseType pingForConfiguration(String logicalAddress, PingForConfigurationType pingForConfigurationType){

		def version =  ApplicationHolder.application.metadata['app.version']
		def grailsVersion =  GrailsUtil.grailsVersion
		def applicationName = ApplicationHolder.application.metadata['app.name']
		def pingDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
		def jvmVersion = System.getProperty('java.version')
		
		def response = new PingForConfigurationResponseType()
		response.setPingDateTime(pingDateTime)
		response.setVersion(version)
		response.getConfiguration().add(createConfigurationProperty("Grails version",grailsVersion))
		response.getConfiguration().add(createConfigurationProperty("Application name",applicationName))
		response.getConfiguration().add(createConfigurationProperty("JVM version",jvmVersion))
		return response
	}

	ConfigurationType createConfigurationProperty(String name, String value){
		def configurationType = new ConfigurationType()
		configurationType.setName(name)
		configurationType.setValue(value)
		return configurationType
	}
}
