package org.callistasoftware.schedulr.services

import grails.test.mixin.*
import org.junit.*

import org.callistasoftware.schedulr.domain.HealthcareFacility;
import org.callistasoftware.schedulr.domain.SubjectOfCare;
import org.callistasoftware.schedulr.domain.Timeslot;
import riv.itintegration.engagementindex._1.ResultCodeEnum
import se.riv.itintegration.engagementindex.update.v1.rivtabp21.UpdateResponderInterface
import se.riv.itintegration.engagementindex.updateresponder.v1.UpdateResponseType
import se.riv.itintegration.engagementindex.updateresponder.v1.UpdateType

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(EngagementIndexService)
class EngagementIndexServiceTests {

    void testSomething() {
		
		def control = mockFor(UpdateResponderInterface)
		control.demand.update{ String logicalAddress, UpdateType params -> 
			UpdateResponseType updateResponse = new UpdateResponseType()
			updateResponse.comment = 'EI Updated'
			updateResponse.resultCode = ResultCodeEnum.OK
			updateResponse
		}
		
		service.updateEngagementIndexClient = control.createMock()
		Timeslot ts = new Timeslot()
		ts.subjectOfCare = new SubjectOfCare()
		ts.healthcareFacility = new HealthcareFacility()
		ts.startTimeInclusive = new Date()
		UpdateResponseType resp = service.updateEngagementIndex(ts)
  
		assert resp.comment.contains('EI Updated')
		assert resp.resultCode == ResultCodeEnum.OK
		
    }
}
