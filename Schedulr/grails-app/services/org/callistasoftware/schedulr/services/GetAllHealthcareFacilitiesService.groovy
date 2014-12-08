package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.HealthcareFacility

import riv.interoperability.headers._1.ActorType
import se.riv.crm.scheduling.getallhealthcarefacilities.v1.rivtabp21.GetAllHealthcareFacilitiesResponderInterface
import se.riv.crm.scheduling.getallhealthcarefacilitiesresponder.v1.GetAllHealthcareFacilitiesResponseType
import se.riv.crm.scheduling.getallhealthcarefacilitiesresponder.v1.GetAllHealthcareFacilitiesType
import se.riv.crm.scheduling.v1.HealthcareFacilityInfoType



@WebService(serviceName = "GetAllHealthcareFacilitiesResponderService",
endpointInterface = "se.riv.crm.scheduling.getallhealthcarefacilities.v1.rivtabp21.GetAllHealthcareFacilitiesResponderInterface",
portName = "GetAllHealthcareFacilitiesPort",
targetNamespace = "urn:riv:crm:scheduling:GetAllHealthcareFacilities:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/GetAllHealthcareFacilitiesInteraction/GetAllHealthcareFacilitiesInteraction_1.1_RIVTABP21.wsdl")
class GetAllHealthcareFacilitiesService implements GetAllHealthcareFacilitiesResponderInterface{
	
	@Override
	public GetAllHealthcareFacilitiesResponseType getAllHealthcareFacilities(
			String logicalAddress, ActorType actor, GetAllHealthcareFacilitiesType getAllHealthCareFacilities) {
		log.info """
				GetAllHealthcareFacilities 
				logicalAddress: $logicalAddress
				healthcareFacility: $getAllHealthCareFacilities.healthcareFacility
				"""
		hasText(getAllHealthCareFacilities.healthcareFacility, 'missing argument "healthcareFacility"')
		hasText(getAllHealthCareFacilities.subjectOfCare, "missing argument \"subjectOfCare\"")
		isTrue(logicalAddress == getAllHealthCareFacilities.healthcareFacility, '"logicalAddress" differs from "healthcare_facility"')
		
		GetAllHealthcareFacilitiesResponseType resp = new GetAllHealthcareFacilitiesResponseType();
		
		def healthcareFacilityInstance = HealthcareFacility.findByHealthcareFacility(getAllHealthCareFacilities.healthcareFacility)
		if (healthcareFacilityInstance) {
			HealthcareFacility.findAll().each {
				resp.healthcareFacilityInfos.add(new HealthcareFacilityInfoType(
					healthcareFacility: it.healthcareFacility,
					healthcareFacilityName: it.healthcareFacilityName))
			}
		} else {
			log.debug "No healthcare facility with id $getAllHealthCareFacilities.healthcareFacility returning empty list of healthcare facilities"
		}
		return resp;
	}

}
