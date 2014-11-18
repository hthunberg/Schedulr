package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.HealthcareFacility
import org.callistasoftware.schedulr.domain.Performer

import riv.interoperability.headers._1.ActorType
import se.riv.crm.scheduling.getallperformers.v1.rivtabp21.GetAllPerformersResponderInterface
import se.riv.crm.scheduling.getallperformersresponder.v1.GetAllPerformersResponseType
import se.riv.crm.scheduling.getallperformersresponder.v1.GetAllPerformersType
import se.riv.crm.scheduling.v1.PerformerInfoType

@WebService(serviceName = "GetAllPerformersResponderService",
endpointInterface = "se.riv.crm.scheduling.getallperformers.v1.rivtabp21.GetAllPerformersResponderInterface",
portName = "GetAllPerformersPort",
targetNamespace = "urn:riv:crm:scheduling:GetAllPerformers:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/GetAllPerformersInteraction/GetAllPerformersInteraction_1.1_RIVTABP21.wsdl")
class GetAllPerformersService implements GetAllPerformersResponderInterface{
	
	@Override
	public GetAllPerformersResponseType getAllPerformers(String logicalAddress,
			ActorType actor, GetAllPerformersType getAllPerformers) {
		log.debug "GetAllPerformers"
		hasText(getAllPerformers.healthcareFacility, 'missing argument "healthcareFacility"')
		hasText(getAllPerformers.subjectOfCare, "missing argument \"subjectOfCare\"")
		isTrue(logicalAddress == getAllPerformers.healthcareFacility, '"logicalAddress" differs from "healthcare_facility"')
		
		GetAllPerformersResponseType resp = new GetAllPerformersResponseType();
		
		def healthcareFacilityInstance = HealthcareFacility.findByHealthcareFacility(getAllPerformers.healthcareFacility)
		if (healthcareFacilityInstance) {
			Performer.findAll().each {
				resp.performerInfos.add(new PerformerInfoType(
					perfomer: it.performerId,
					firstName: it.firstName,
					lastName: it.lastName,
					title: it.title)
				)
			}
		} else {
			log.debug "No healthcare facility with id $getAllPerformers.healthcareFacility returning empty list of performers"
		}
		return resp;
	}
	

}
