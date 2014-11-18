package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.HealthcareFacility
import org.callistasoftware.schedulr.types.Timetype

import riv.interoperability.headers._1.ActorType
import se.riv.crm.scheduling.getalltimetypes.v1.rivtabp21.GetAllTimeTypesResponderInterface
import se.riv.crm.scheduling.getalltimetypesresponder.v1.GetAllTimeTypesResponseType
import se.riv.crm.scheduling.getalltimetypesresponder.v1.GetAllTimeTypesType
import se.riv.crm.scheduling.v1.TimeTypeType



@WebService(serviceName = "GetAllTimeTypesResponderService",
endpointInterface = "se.riv.crm.scheduling.getalltimetypes.v1.rivtabp21.GetAllTimeTypesResponderInterface",
portName = "GetAllTimeTypesPort",
targetNamespace = "urn:riv:crm:scheduling:GetAllTimeTypes:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/GetAllTimeTypesInteraction/GetAllTimeTypesInteraction_1.1_RIVTABP21.wsdl")
class GetAllTimeTypesService implements GetAllTimeTypesResponderInterface{

	
	@Override
	public GetAllTimeTypesResponseType getAllTimeTypes(String logicalAddress,
			ActorType actor, GetAllTimeTypesType getAllTimeTypes) {
		log.debug "GetAllTimeTypes"
		
		hasText(getAllTimeTypes.healthcareFacility, 'missing argument "healthcareFacility"')
		hasText(getAllTimeTypes.subjectOfCare, "missing argument \"subjectOfCare\"")
		isTrue(logicalAddress == getAllTimeTypes.healthcareFacility, '"logicalAddress" differs from "healthcareFacility"')
		
		GetAllTimeTypesResponseType resp = new GetAllTimeTypesResponseType()
		
		def healthcareFacilityInstance = HealthcareFacility.findByHealthcareFacility(getAllTimeTypes.healthcareFacility)
		if (healthcareFacilityInstance) {
			for (c in Timetype.values()) {
				resp.getListOfTimeTypes().add(new TimeTypeType(timeTypeId: c.id, timeTypeName: c.name))
			}
		} else {
			log.debug "No healthcare facility with id $getAllTimeTypes.healthcareFacility returning empty list of time types"
		}
		return resp;
		
	}
	
}
