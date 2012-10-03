package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.Timeslot

import riv.interoperability.headers._1.ActorType
import se.riv.crm.scheduling.getbookingdetails.v1.rivtabp21.GetBookingDetailsResponderInterface
import se.riv.crm.scheduling.getbookingdetailsresponder.v1.GetBookingDetailsResponseType
import se.riv.crm.scheduling.getbookingdetailsresponder.v1.GetBookingDetailsType



@WebService(serviceName = "GetBookingDetailsResponderService",
endpointInterface = "se.riv.crm.scheduling.getbookingdetails.v1.rivtabp21.GetBookingDetailsResponderInterface",
portName = "GetBookingDetailsPort",
targetNamespace = "urn:riv:crm:scheduling:GetBookingDetails:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/GetBookingDetailsInteraction/GetBookingDetailsInteraction_1.1_RIVTABP21.wsdl")
class GetBookingDetailsService implements GetBookingDetailsResponderInterface{


	/**
	 * Get one time slot represented by bookingId.
	 */
	@Override
	public GetBookingDetailsResponseType getBookingDetails(
	String logicalAddress, ActorType actor, GetBookingDetailsType getBookingDetails) {
		hasText(getBookingDetails.bookingId, "missing argument \"bookingId\"")
		hasText(getBookingDetails.healthcareFacility, 'missing argument "healthcareFacility"')
		isTrue(logicalAddress == getBookingDetails.healthcareFacility, '"logicalAddress" differs from "healthcareFacility"')
		
		log.debug("Get booking details for booking id ${getBookingDetails.bookingId} " +
			"and health care facility ${getBookingDetails.healthcareFacility}")

		def timeslot = getTimeslotWithBookingId(getBookingDetails.bookingId, getBookingDetails.healthcareFacility)
		return ResponseBuilder.buildBookingDetails(timeslot);
	}

	private Timeslot getTimeslotWithBookingId(String bookingId, String healthcareFacilityId){
		Timeslot.find {
			id == bookingId && healthcareFacility.healthcareFacility == healthcareFacilityId
		}
	}
}
