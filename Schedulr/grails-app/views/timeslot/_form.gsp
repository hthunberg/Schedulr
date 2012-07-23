<%@ page import="org.callistasoftware.schedulr.domain.Timeslot" %>



<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'startTimeInclusive', 'error')} required">
	<label for="startTimeInclusive">
		<g:message code="startTimeInclusive.label" default="Start Time Inclusive" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startTimeInclusive" precision="minute"  value="${timeslotInstance?.startTimeInclusive}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'endTimeExclusive', 'error')} required">
	<label for="endTimeExclusive">
		<g:message code="endTimeExclusive.label" default="End Time Exclusive" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endTimeExclusive" precision="minute"  value="${timeslotInstance?.endTimeExclusive}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'cancelBookingAllowed', 'error')} ">
	<label for="cancelBookingAllowed">
		<g:message code="cancelBookingAllowed.label" default="Cancel Booking Allowed" />
		
	</label>
	<g:checkBox name="cancelBookingAllowed" value="${timeslotInstance?.cancelBookingAllowed}" />
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'rebookingAllowed', 'error')} ">
	<label for="rebookingAllowed">
		<g:message code="rebookingAllowed.label" default="Rebooking Allowed" />
		
	</label>
	<g:checkBox name="rebookingAllowed" value="${timeslotInstance?.rebookingAllowed}" />
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'isInvitation', 'error')} ">
	<label for="isInvitation">
		<g:message code="isInvitation.label" default="Is Invitation" />
		
	</label>
	<g:checkBox name="isInvitation" value="${timeslotInstance?.isInvitation}" />
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'careType', 'error')} ">
	<label for="careType">
		<g:message code="caretype.label" default="Care Type" />
	</label>
	<g:select name="caretype" from="${org.callistasoftware.schedulr.types.Caretype?.values()}" value="${timeslotInstance?.caretype?.key}" optionKey="key" noSelection="['':'']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'healthcareFacility', 'error')} required">
	<label for="healthcareFacility">
		<g:message code="healthcareFacility.label" default="Healthcare Facility" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="healthcareFacility" name="healthcareFacility.id" from="${org.callistasoftware.schedulr.domain.HealthcareFacility.list()}" optionKey="id" required="" value="${timeslotInstance?.healthcareFacility?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'performer', 'error')} ">
	<label for="performer">
		<g:message code="performer.label" default="Performer" />
		
	</label>
	<g:select id="performer" name="performer.id" from="${org.callistasoftware.schedulr.domain.Performer.list()}" optionKey="id" value="${timeslotInstance?.performer?.id}" class="many-to-one" noSelection="${['null':'']}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'purpose', 'error')} ">
	<label for="purpose">
		<g:message code="purpose.label" default="Purpose" />
		
	</label>
	<g:textField name="purpose" value="${timeslotInstance?.purpose}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'reason', 'error')} ">
	<label for="reason">
		<g:message code="reason.label" default="Reason" />
		
	</label>
	<g:textField name="reason" value="${timeslotInstance?.reason}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'resourceName', 'error')} ">
	<label for="resourceName">
		<g:message code="resourceName.label" default="Resource Name" />
	</label>
	<g:textField name="resourceName" value="${timeslotInstance?.resourceName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'resourceId', 'error')} ">
	<label for="resourceId">
		<g:message code="resourceId.label" default="Resource Id" />
	</label>
	<g:textField name="resourceId" value="${timeslotInstance?.resourceId}" />
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'subjectOfCare', 'error')} required">
	<label for="subjectOfCare">
		<g:message code="subjectOfCare.label" default="Subject Of Care" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="subjectOfCare" name="subjectOfCare.id" from="${org.callistasoftware.schedulr.domain.SubjectOfCare.list()}" optionKey="id" required="" value="${timeslotInstance?.subjectOfCare?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: timeslotInstance, field: 'timetype', 'error')} ">
	<label for="timetype">
		<g:message code="timetype.label" default="Time Type" />
	</label>
	<g:select name="timetype" from="${org.callistasoftware.schedulr.types.Timetype?.values()}" value="${timeslotInstance?.timetype?.key}" optionKey="key" noSelection="['':'']"/>
</div>



