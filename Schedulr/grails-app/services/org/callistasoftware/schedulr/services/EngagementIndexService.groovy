package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import org.callistasoftware.schedulr.domain.Timeslot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.Assert;

import riv.itintegration.engagementindex._1.EngagementTransactionType
import riv.itintegration.engagementindex._1.EngagementType
import se.riv.itintegration.engagementindex.update.v1.rivtabp21.UpdateResponderInterface
import se.riv.itintegration.engagementindex.updateresponder.v1.UpdateResponseType
import se.riv.itintegration.engagementindex.updateresponder.v1.UpdateType

class EngagementIndexService {

	@Autowired(required = false)
	UpdateResponderInterface updateEngagementIndexClient;
	
	def grailsApplication
	
	/**
	 * Used for updating the engagement index when a booking/invitation has changed.
	 * Delete true if the cahnage was a delete and false if the change was a create or update. 
	 */
	def updateEngagementIndex = { Timeslot timeslot, boolean delete=false ->
		if (!grailsApplication.config.engagementindex.updateEngagementIndex) {
			return
		}
		Assert.notNull(timeslot.subjectOfCare, 'missing subjectOfCare on booking')
		Assert.notNull(timeslot.healthcareFacility, 'missing healthcareFacility on booking')
		UpdateType type = new UpdateType();
		EngagementTransactionType eiTx = new EngagementTransactionType();
		
		EngagementType eiType = new EngagementType();
		eiType.setRegisteredResidentIdentification(timeslot.subjectOfCare.subjectOfCareId)
		eiType.setServiceDomain('riv:crm:scheduling')
		eiType.setSourceSystem(grailsApplication.config.schedulr.hsaId)
		if (timeslot.isInvitation) {
			eiType.setCategorization('Invitation')
		} else {
			eiType.setCategorization('Booking')
		}
		eiType.setClinicalProcessInterestId("NA")
		eiType.setLogicalAddress(timeslot.healthcareFacility.healthcareFacility) // the hsaId of the healthcare facility
		eiType.setBusinessObjectInstanceIdentifier("${timeslot.id}")
		//eiType.setClinicalProcessInterestId() // Not used in the crm:scheduling domain
		if (timeslot.isInvitation && !timeslot.getStartTimeInclusive()) {
			eiType.setMostRecentContent(timeslot.dateCreated.format("yyyyMMddHHmmss"))
		} else {
			eiType.setMostRecentContent(timeslot.getStartTimeInclusive().format("yyyyMMddHHmmss"))
		}
		
		eiTx.setEngagement(eiType);
		eiTx.setDeleteFlag(delete)
		
		type.getEngagementTransaction().add(eiTx)
		UpdateResponseType resp = updateEngagementIndexClient.update(grailsApplication.config.engagementindex.logicalAddress, type)
		resp
	}
}
