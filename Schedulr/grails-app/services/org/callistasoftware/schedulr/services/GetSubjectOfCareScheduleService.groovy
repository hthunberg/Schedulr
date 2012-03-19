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
		hasText(getSubjectOfCareSchedule.healthcareFacility, "missing argument \"healthcareFacility\"")
		hasText(getSubjectOfCareSchedule.subjectOfCare, "missing argument \"subject_of_care\"")

		List timeSlots = getTimeSlotsForSubjectOfCareAndFacility(getSubjectOfCareSchedule)
		return ResponseBuilder.buildSubjectOfCareSchedule(timeSlots);
	}

	private List getTimeSlotsForSubjectOfCareAndFacility(GetSubjectOfCareScheduleType getSubjectOfCareSchedule) {
		def subjectOfCareId = getSubjectOfCareSchedule.subjectOfCare
		def healtcareFacilityId = getSubjectOfCareSchedule.healthcareFacility

		def subjectOfCare = SubjectOfCare.findBySubjectOfCareId(subjectOfCareId);
		def healtcareFacility = HealthcareFacility.findByHealthcareFacility(healtcareFacilityId);

		List timeSlots = Timeslot.findAllBySubjectOfCareAndHealthcareFacility(subjectOfCare, healtcareFacility)
		return timeSlots
	}
}
