package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.HealthcareFacility
import org.callistasoftware.schedulr.domain.Timeslot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException

import se.riv.crm.scheduling.cancelbooking.v1.rivtabp21.CancelBookingResponderInterface
import se.riv.crm.scheduling.cancelbookingresponder.v1.CancelBookingResponseType
import se.riv.crm.scheduling.cancelbookingresponder.v1.CancelBookingType
import se.riv.crm.scheduling.v1.ResultCodeEnum
import se.riv.interoperability.headers.v1.ActorType

@WebService(serviceName = "CancelBookingResponderService",
endpointInterface = "se.riv.crm.scheduling.cancelbooking.v1.rivtabp21.CancelBookingResponderInterface",
portName = "CancelBookingPort",
targetNamespace = "urn:riv:crm:scheduling:CancelBooking:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/CancelBookingInteraction/CancelBookingInteraction_1.1_RIVTABP21.wsdl")
class CancelBookingService implements CancelBookingResponderInterface {

	@Autowired(required = false)
	EngagementIndexService engagementIndexService
	
	@Override
	public CancelBookingResponseType cancelBooking(String logicalAddress, ActorType actor,
			CancelBookingType cancelBooking) {
		log.info """
				CancelBooking 
				healthcareFacility: $cancelBooking.healthcareFacility 
				booking: $cancelBooking.bookingId 
				message: $cancelBooking.message
				"""
		CancelBookingResponseType resp = new CancelBookingResponseType();
        def healthcareFacilityInstance = HealthcareFacility.findByHealthcareFacility(cancelBooking.healthcareFacility)
		if (!healthcareFacilityInstance) {
			resp.setResultCode(ResultCodeEnum.ERROR);
			resp.setResultText("HealthcareFacility $cancelBooking.healthcareFacility not found");
			return resp
		}
		def timeslotInstance = Timeslot.findByIdAndHealthcareFacility(cancelBooking.bookingId, healthcareFacilityInstance)
        if (!timeslotInstance) {
			resp.setResultCode(ResultCodeEnum.ERROR);
			resp.setResultText("Booking not found with id $cancelBooking.bookingId at healthcareFacility $cancelBooking.healthcareFacility");
            return resp
        }

        try {
            timeslotInstance.delete(flush: true)
			engagementIndexService.updateEngagementIndex(timeslotInstance, true)
			resp.setResultCode(ResultCodeEnum.OK);
			resp.setResultText("Deleted booking with id $cancelBooking.bookingId");
			return resp
			
        }
        catch (DataIntegrityViolationException e) {
			resp.setResultCode(ResultCodeEnum.ERROR);
			resp.setResultText("Booking with id $cancelBooking.bookingId could not be deleted");
			return resp
        }
		return null;
	}

}
