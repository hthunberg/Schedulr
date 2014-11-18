package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.HealthcareFacility;
import org.callistasoftware.schedulr.domain.Timeslot
import org.callistasoftware.schedulr.types.Caretype;

import riv.interoperability.headers._1.ActorType
import se.riv.crm.scheduling.getallcaretypes.v1.rivtabp21.GetAllCareTypesResponderInterface
import se.riv.crm.scheduling.getallcaretypesresponder.v1.GetAllCareTypesResponseType
import se.riv.crm.scheduling.getallcaretypesresponder.v1.GetAllCareTypesType
import se.riv.crm.scheduling.v1.CareTypeType;



@WebService(serviceName = "GetAllCareTypesResponderService",
endpointInterface = "se.riv.crm.scheduling.getallcaretypes.v1.rivtabp21.GetAllCareTypesResponderInterface",
portName = "GetAllCareTypesPort",
targetNamespace = "urn:riv:crm:scheduling:GetAllCareTypes:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/GetAllCareTypesInteraction/GetAllCareTypesInteraction_1.1_RIVTABP21.wsdl")
class GetAllCareTypesService implements GetAllCareTypesResponderInterface{


	 @Override
	public GetAllCareTypesResponseType getAllCareTypes(ActorType actor,
			String logicalAddress, GetAllCareTypesType getAllCareTypes) {
			log.debug "GetAllCareTypes"
			
			hasText(getAllCareTypes.healthcareFacility, 'missing argument "healthcareFacility"')
			hasText(getAllCareTypes.subjectOfCare, "missing argument \"subjectOfCare\"")
			isTrue(logicalAddress == getAllCareTypes.healthcareFacility, '"logicalAddress" differs from "healthcareFacility"')
			
			GetAllCareTypesResponseType resp = new GetAllCareTypesResponseType()
			
			def healthcareFacilityInstance = HealthcareFacility.findByHealthcareFacility(getAllCareTypes.healthcareFacility)
			if (healthcareFacilityInstance) {
				for (c in Caretype.values()) {
					resp.getCareTypes().add(new CareTypeType(careTypeId: c.id, careTypeName: c.name))
				}
			} else {
				log.debug "No healthcare facility with id $getAllCareTypes.healthcareFacility returning empty list of care types"
			}
			return resp;
	}
	
}
