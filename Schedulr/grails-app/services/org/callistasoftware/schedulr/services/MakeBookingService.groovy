package org.callistasoftware.schedulr.services

import static org.springframework.util.Assert.*

import javax.jws.WebService

import org.callistasoftware.schedulr.domain.Timeslot

import riv.interoperability.headers._1.ActorType
import se.riv.crm.scheduling.getbookingdetails.v1.rivtabp21.GetBookingDetailsResponderInterface
import se.riv.crm.scheduling.getbookingdetailsresponder.v1.GetBookingDetailsResponseType
import se.riv.crm.scheduling.getbookingdetailsresponder.v1.GetBookingDetailsType
import se.riv.crm.scheduling.makebooking.v1.rivtabp21.MakeBookingResponderInterface;
import se.riv.crm.scheduling.makebookingresponder.v1.MakeBookingResponseType;
import se.riv.crm.scheduling.makebookingresponder.v1.MakeBookingType;



@WebService(serviceName = "MakeBookingResponderService",
endpointInterface = "se.riv.crm.scheduling.makebooking.v1.rivtabp21.MakeBookingResponderInterface",
portName = "MakeBookingPort",
targetNamespace = "urn:riv:crm:scheduling:MakeBooking:1:rivtabp21",
wsdlLocation = "WEB-INF/wsdl/schemas/interactions/MakeBookingInteraction/MakeBookingInteraction_1.1_RIVTABP21.wsdl")
class MakeBookingService implements MakeBookingResponderInterface {

	
	@Override
	public MakeBookingResponseType makeBooking(String logicalAddress, ActorType actor,
			MakeBookingType makeBooking) {
		log.info """\
			MakeBooking
			healthcareFacility: $makeBooking.healthcareFacilityMed
			start: $makeBooking.requestedTimeslot.startTimeInclusive
			end: $makeBooking.requestedTimeslot.startTimeInclusive
			"""
		// TODO Auto-generated method stub
		return null;
	}

}
