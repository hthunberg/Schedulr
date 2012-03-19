
<%@ page import="org.callistasoftware.schedulr.domain.SubjectOfCare" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'subjectOfCare.label', default: 'SubjectOfCare')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-subjectOfCare" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-subjectOfCare" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list subjectOfCare">
			
				<g:if test="${subjectOfCareInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${subjectOfCareInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${subjectOfCareInstance?.middleName}">
				<li class="fieldcontain">
					<span id="middleName-label" class="property-label"><g:message code="middleName.label" default="Middle Name" /></span>
					
						<span class="property-value" aria-labelledby="middleName-label"><g:fieldValue bean="${subjectOfCareInstance}" field="middleName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${subjectOfCareInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${subjectOfCareInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				
			
				<g:if test="${subjectOfCareInstance?.subjectOfCareId}">
				<li class="fieldcontain">
					<span id="subjectOfCareId-label" class="property-label"><g:message code="subjectOfCareId.label" default="Subject Of Care Id" /></span>
					
						<span class="property-value" aria-labelledby="subjectOfCareId-label"><g:fieldValue bean="${subjectOfCareInstance}" field="subjectOfCareId"/></span>
					
				</li>
				</g:if>
			
				
			
				<g:if test="${subjectOfCareInstance?.homeAddress}">
				<li class="fieldcontain">
					<span id="homeAddress-label" class="property-label"><g:message code="homeAddress.label" default="Home Address" /></span>
					
						<span class="property-value" aria-labelledby="homeAddress-label"><g:fieldValue bean="${subjectOfCareInstance}" field="homeAddress"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${subjectOfCareInstance?.coAddress}">
				<li class="fieldcontain">
					<span id="coAddress-label" class="property-label"><g:message code="coAddress.label" default="Co Address" /></span>
					
						<span class="property-value" aria-labelledby="coAddress-label"><g:fieldValue bean="${subjectOfCareInstance}" field="coAddress"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${subjectOfCareInstance?.phone}">
				<li class="fieldcontain">
					<span id="phone-label" class="property-label"><g:message code="phone.label" default="Phone" /></span>
					
						<span class="property-value" aria-labelledby="phone-label"><g:fieldValue bean="${subjectOfCareInstance}" field="phone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${subjectOfCareInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${subjectOfCareInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
			
			
				<g:if test="${subjectOfCareInstance?.timeSlots}">
				<li class="fieldcontain">
					<span id="timeSlots-label" class="property-label"><g:message code="timeSlots.label" default="Time Slots" /></span>
					
						<g:each in="${subjectOfCareInstance.timeSlots}" var="t">
						<span class="property-value" aria-labelledby="timeSlots-label"><g:link controller="timeslot" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${subjectOfCareInstance?.id}" />
					<g:link class="edit" action="edit" id="${subjectOfCareInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
