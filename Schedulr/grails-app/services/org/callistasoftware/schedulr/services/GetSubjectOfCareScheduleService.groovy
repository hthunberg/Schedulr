package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.HealthcareFacility
import org.callistasoftware.schedulr.domain.SubjectOfCare
import org.callistasoftware.schedulr.domain.Timeslot

import riv.interoperability.headers._1.ActorType
import se.riv.crm.scheduling.getsubjectofcareschedule.v1.rivtabp21.GetSubjectOfCareScheduleResponderInterface
import se.riv.crm.scheduling.getsubjectofcarescheduleresponder.v1.GetSubjectOfCareScheduleResponseType
import se.riv.crm.scheduling.getsubjectofcarescheduleresponder.v1.GetSubjectOfCareScheduleType


@WebService(serviceName = "GetSubjectOfCareScheduleResponderService",
endpointInterface = "se.riv.crm.scheduling.getsubjectofcareschedule.v1.rivtabp21.GetSubjectOfCareScheduleResponderInterface",
portName = "GetSubjectOfCareSchedulePort",
targetNamespace = "urn:riv:crm:scheduling:GetSubjectOfCareSchedule:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/GetSubjectOfCareScheduleInteraction/GetSubjectOfCareScheduleInteraction_1.1_RIVTABP21.wsdl")
class GetSubjectOfCareScheduleService implements GetSubjectOfCareScheduleResponderInterface{

	/**
	 * Get all scheduled activities for a subject of care.
	 */
	@Override
	public GetSubjectOfCareScheduleResponseType getSubjectOfCareSchedule(
	String logicalAddress, ActorType actor, GetSubjectOfCareScheduleType getSubjectOfCareSchedule) {
		
		hasText(getSubjectOfCareSchedule.healthcareFacility, "missing argument \"healthcare_facility\"")
		hasText(getSubjectOfCareSchedule.subjectOfCare, "missing argument \"subject_of_care\"")
		isTrue(logicalAddress == getSubjectOfCareSchedule.healthcareFacility, '"logicalAddress" differs from "healthcareFacility"')
		
		log.debug("Get booking details for subject of care ${getSubjectOfCareSchedule.subjectOfCare} " +
			"at healthcare facility ${getSubjectOfCareSchedule.healthcareFacility}")

		List timeSlots = getTimeSlots(getSubjectOfCareSchedule)
		return ResponseBuilder.buildSubjectOfCareSchedule(timeSlots);
	}

	private List getTimeSlots(GetSubjectOfCareScheduleType getSubjectOfCareSchedule) {
		String subjectOfCareId = getSubjectOfCareSchedule.subjectOfCare
		String healtcareFacilityId = getSubjectOfCareSchedule.healthcareFacility

		List timeSlots = null;

		SubjectOfCare subjectOfCare = SubjectOfCare.findBySubjectOfCareId(subjectOfCareId);

		if(subjectOfCare){
			if(healtcareFacilityId){
				def healtcareFacility = HealthcareFacility.findByHealthcareFacility(healtcareFacilityId);
				timeSlots = Timeslot.findAllBySubjectOfCareAndHealthcareFacility(subjectOfCare, healtcareFacility)
			}else{
				timeSlots = Timeslot.findAllBySubjectOfCare(subjectOfCare)
			}
		}

		return timeSlots
	}
}
