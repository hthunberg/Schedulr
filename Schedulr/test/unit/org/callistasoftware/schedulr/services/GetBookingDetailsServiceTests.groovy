package org.callistasoftware.schedulr.services

import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin

import org.callistasoftware.schedulr.domain.HealthcareFacility
import org.callistasoftware.schedulr.domain.Performer
import org.callistasoftware.schedulr.domain.SubjectOfCare
import org.callistasoftware.schedulr.domain.Timeslot
import org.junit.*

import se.riv.crm.scheduling.getbookingdetailsresponder.v1.GetBookingDetailsResponseType;
import se.riv.crm.scheduling.getbookingdetailsresponder.v1.GetBookingDetailsType;
import se.riv.crm.scheduling.getsubjectofcarescheduleresponder.v1.GetSubjectOfCareScheduleResponseType
import se.riv.crm.scheduling.getsubjectofcarescheduleresponder.v1.GetSubjectOfCareScheduleType

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GetBookingDetailsService)
@TestMixin(DomainClassUnitTestMixin)
@Mock([HealthcareFacility, SubjectOfCare, Timeslot, Performer])
class GetBookingDetailsServiceTests {

    void testIncorrectHealthcareFacilityAndLogicalAddress() {
		mockData()
		GetBookingDetailsType parameters = new GetBookingDetailsType();
		parameters.setHealthcareFacility('HSA-3');
		parameters.setBookingId('1');
		GetBookingDetailsResponseType resp = service.getBookingDetails('HSA-3', null, parameters);
		assert !resp.getTimeslotDetail()
    }
	
	void testCorrectHealthcareFacilityAndLogicalAddress() {
		mockData()
		GetBookingDetailsType parameters = new GetBookingDetailsType();
		parameters.setHealthcareFacility('HSA-1');
		parameters.setBookingId('1');
		GetBookingDetailsResponseType resp = service.getBookingDetails('HSA-1', null, parameters);
		assert resp.getTimeslotDetail()
		assert resp.getTimeslotDetail().reason == 'Muscle training 1'
	}
	
	void testHealthcareFacilityNotEqualToLogicalAddress() {
		mockData()
		GetBookingDetailsType parameters = new GetBookingDetailsType();
		parameters.setHealthcareFacility('HSA-3');
		parameters.setBookingId('1');
		try {
			service.getBookingDetails('HSA-1', null, parameters);
			assert false
		} catch (IllegalArgumentException e) {
		}
	}
	
	void testOnlyLogicalAddress() {
		mockData()
		GetBookingDetailsType parameters = new GetBookingDetailsType();
		parameters.setBookingId('1');
		try {
			service.getBookingDetails('HSA-1', null, parameters);
			assert false
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
