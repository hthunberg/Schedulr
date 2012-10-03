package org.callistasoftware.schedulr.services

import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin

import org.callistasoftware.schedulr.domain.HealthcareFacility
import org.callistasoftware.schedulr.domain.Performer;
import org.callistasoftware.schedulr.domain.SubjectOfCare
import org.callistasoftware.schedulr.domain.Timeslot
import org.junit.*

import se.riv.crm.scheduling.getsubjectofcarescheduleresponder.v1.GetSubjectOfCareScheduleResponseType
import se.riv.crm.scheduling.getsubjectofcarescheduleresponder.v1.GetSubjectOfCareScheduleType

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GetSubjectOfCareScheduleService)
@TestMixin(DomainClassUnitTestMixin)
@Mock([HealthcareFacility, SubjectOfCare, Timeslot, Performer])
class GetSubjectOfCareScheduleServiceTests {

    void testHealthcareFacilityAndLogicalAddress() {
		mockData()
		GetSubjectOfCareScheduleType parameters = new GetSubjectOfCareScheduleType();
		parameters.setHealthcareFacility('HSA-3');
		parameters.setSubjectOfCare('191212121212');
		GetSubjectOfCareScheduleResponseType resp = service.getSubjectOfCareSchedule('HSA-3', null, parameters);
		assertEquals 2, resp.getTimeslotDetail().size()
		def ids = ['2','3'] as Set // We expect timeslot with id 2 and 3 to be found since they are from healthcareFacility HSA-3
		assert ids.remove(resp.timeslotDetail.get(0).bookingId)
		assertEquals('HSA-3', resp.timeslotDetail.get(0).healthcareFacility)
		assert ids.remove(resp.timeslotDetail.get(1).bookingId)
		assertEquals('HSA-3', resp.timeslotDetail.get(1).healthcareFacility)
		assertTrue(ids.isEmpty())
    }
	
	void testHealthcareFacilityNotEqualToLogicalAddress() {
		mockData()
		GetSubjectOfCareScheduleType parameters = new GetSubjectOfCareScheduleType();
		parameters.setHealthcareFacility('HSA-3');
		parameters.setSubjectOfCare('191212121212');
		
		try {
			GetSubjectOfCareScheduleResponseType resp = service.getSubjectOfCareSchedule('HSA-1', null, parameters);
			fail
		} catch (IllegalArgumentException e) {
		}
	}
	
	void testOnlyLogicalAddress() {
		mockData()
		GetSubjectOfCareScheduleType parameters = new GetSubjectOfCareScheduleType();
		parameters.setSubjectOfCare('191212121212');
		try {
			GetSubjectOfCareScheduleResponseType resp = service.getSubjectOfCareSchedule('HSA-1', null, parameters);
			fail
		} catch (IllegalArgumentException e) {
		}
	}
	
	def mockData = {
		mockDomain(HealthcareFacility, [
			[id: 1, healthcareFacility: 'HSA-1', healthcareFacilityName: 'Healthcare facility number 1'],
			[id: 2, healthcareFacility: 'HSA-2', healthcareFacilityName: 'Healthcare facility number 2'],
			[id: 3, healthcareFacility: 'HSA-3', healthcareFacilityName: 'Healthcare facility number 3'] ])
	
		mockDomain(SubjectOfCare, [
			[ id: 1, subjectOfCareId: '191212121212', firstName: 'Kalle', lastName: 'Kula',
			phone: '123', email: 'test@test.test', homeAddress: 'Test street']
		])
	
		mockDomain(Performer, [
			[id: 1, firstName: 'Test', lastName: 'Doktorsson', title: 'Dr', performerId: 'HSA-99']
			])
		
		mockDomain(Timeslot, [
		[id: 1, startTimeInclusive: Date.parse('yyyyMMdd HHmmss','20121002 120000'),
			endTimeExclusive: Date.parse('yyyyMMdd HHmmss','20121002 130000'),
			subjectOfCare: SubjectOfCare.get(1),
			healthcareFacility: HealthcareFacility.get(1),
			performer: Performer.get(1),
			purpose: 'Rehab',
			reason: 'Muscle training 1'],
		[id: 2, startTimeInclusive: Date.parse('yyyyMMdd HHmmss','20121003 120000'),
			endTimeExclusive: Date.parse('yyyyMMdd HHmmss','20121003 130000'),
			subjectOfCare: SubjectOfCare.get(1),
			healthcareFacility: HealthcareFacility.get(3),
			performer: Performer.get(1),
			purpose: 'Rehab',
			reason: 'Muscle training 2'],
		[id: 3, startTimeInclusive: Date.parse('yyyyMMdd HHmmss','20121004 120000'),
			endTimeExclusive: Date.parse('yyyyMMdd HHmmss','20121004 130000'),
			subjectOfCare: SubjectOfCare.get(1),
			healthcareFacility: HealthcareFacility.get(3),
			performer: Performer.get(1),
			purpose: 'Rehab',
			reason: 'Muscle training 3']
		])

		assertEquals 1, SubjectOfCare.count()
		assertEquals '123', Timeslot.get(1).subjectOfCare.phone
		assertEquals 3, Timeslot.count()
		assert HealthcareFacility.count == 3
		assert Timeslot.get(1).healthcareFacility.healthcareFacility == 'HSA-1'
	}
}
