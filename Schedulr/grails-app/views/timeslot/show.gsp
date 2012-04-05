
<%@ page import="org.callistasoftware.schedulr.domain.Timeslot" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'timeslot.label', default: 'Timeslot')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-timeslot" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-timeslot" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list timeslot">
			
				<g:if test="${timeslotInstance?.startTimeInclusive}">
				<li class="fieldcontain">
					<span id="startTimeInclusive-label" class="property-label"><g:message code="startTimeInclusive.label" default="Start Time Inclusive" /></span>
					
						<span class="property-value" aria-labelledby="startTimeInclusive-label"><g:formatDate date="${timeslotInstance?.startTimeInclusive}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.endTimeExclusive}">
				<li class="fieldcontain">
					<span id="endTimeExclusive-label" class="property-label"><g:message code="endTimeExclusive.label" default="End Time Exclusive" /></span>
					
						<span class="property-value" aria-labelledby="endTimeExclusive-label"><g:formatDate date="${timeslotInstance?.endTimeExclusive}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.cancelBookingAllowed}">
				<li class="fieldcontain">
					<span id="cancelBookingAllowed-label" class="property-label"><g:message code="cancelBookingAllowed.label" default="Cancel Booking Allowed" /></span>
					
						<span class="property-value" aria-labelledby="cancelBookingAllowed-label"><g:formatBoolean boolean="${timeslotInstance?.cancelBookingAllowed}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.caretype}">
				<li class="fieldcontain">
					<span id="caretype-label" class="property-label"><g:message code="caretype.label" default="Care Type" /></span>
					
						<span class="property-value" aria-labelledby="careType-label"><g:fieldValue bean="${timeslotInstance}" field="caretype"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.healthcareFacility}">
				<li class="fieldcontain">
					<span id="healthcareFacility-label" class="property-label"><g:message code="healthcareFacility.label" default="Healthcare Facility" /></span>
					
						<span class="property-value" aria-labelledby="healthcareFacility-label"><g:link controller="healthcareFacility" action="show" id="${timeslotInstance?.healthcareFacility?.id}">${timeslotInstance?.healthcareFacility?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.isInvitation}">
				<li class="fieldcontain">
					<span id="isInvitation-label" class="property-label"><g:message code="isInvitation.label" default="Is Invitation" /></span>
					
						<span class="property-value" aria-labelledby="isInvitation-label"><g:formatBoolean boolean="${timeslotInstance?.isInvitation}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.performer}">
				<li class="fieldcontain">
					<span id="performer-label" class="property-label"><g:message code="performer.label" default="Performer" /></span>
					
						<span class="property-value" aria-labelledby="performer-label"><g:link controller="performer" action="show" id="${timeslotInstance?.performer?.id}">${timeslotInstance?.performer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.purpose}">
				<li class="fieldcontain">
					<span id="purpose-label" class="property-label"><g:message code="purpose.label" default="Purpose" /></span>
					
						<span class="property-value" aria-labelledby="purpose-label"><g:fieldValue bean="${timeslotInstance}" field="purpose"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.reason}">
				<li class="fieldcontain">
					<span id="reason-label" class="property-label"><g:message code="reason.label" default="Reason" /></span>
					
						<span class="property-value" aria-labelledby="reason-label"><g:fieldValue bean="${timeslotInstance}" field="reason"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.rebookingAllowed}">
				<li class="fieldcontain">
					<span id="rebookingAllowed-label" class="property-label"><g:message code="rebookingAllowed.label" default="Rebooking Allowed" /></span>
					
						<span class="property-value" aria-labelledby="rebookingAllowed-label"><g:formatBoolean boolean="${timeslotInstance?.rebookingAllowed}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.resourceId}">
				<li class="fieldcontain">
					<span id="resourceid-label" class="property-label"><g:message code="resourceId.label" default="Resource Id" /></span>
					
						<span class="property-value" aria-labelledby="resourceid-label"><g:fieldValue bean="${timeslotInstance}" field="resourceId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.resourceName}">
				<li class="fieldcontain">
					<span id="resourceName-label" class="property-label"><g:message code="resourceName.label" default="Resource Name" /></span>
					
						<span class="property-value" aria-labelledby="resourceName-label"><g:fieldValue bean="${timeslotInstance}" field="resourceName"/></span>
					
				</li>
				</g:if>			
			
				<g:if test="${timeslotInstance?.subjectOfCare}">
				<li class="fieldcontain">
					<span id="subjectOfCare-label" class="property-label"><g:message code="subjectOfCare.label" default="Subject Of Care" /></span>
					
						<span class="property-value" aria-labelledby="subjectOfCare-label"><g:link controller="subjectOfCare" action="show" id="${timeslotInstance?.subjectOfCare?.id}">${timeslotInstance?.subjectOfCare?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${timeslotInstance?.timetype}">
				<li class="fieldcontain">
					<span id="timetype-label" class="property-label"><g:message code="timetype.label" default="Time Type" /></span>
					
						<span class="property-value" aria-labelledby="timetype-label"><g:fieldValue bean="${timeslotInstance}" field="timetype"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${timeslotInstance?.id}" />
					<g:link class="edit" action="edit" id="${timeslotInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
