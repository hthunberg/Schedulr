
<%@ page import="org.callistasoftware.schedulr.domain.Performer" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'performer.label', default: 'Performer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-performer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-performer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list performer">
			
				<g:if test="${performerInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${performerInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${performerInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${performerInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${performerInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${performerInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${performerInstance?.performerId}">
				<li class="fieldcontain">
					<span id="performerId-label" class="property-label"><g:message code="performerId.label" default="PerformerId" /></span>
					
						<span class="property-value" aria-labelledby="performerId-label"><g:fieldValue bean="${performerInstance}" field="performerId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${performerInstance?.timeSlots}">
				<li class="fieldcontain">
					<span id="timeSlots-label" class="property-label"><g:message code="timeSlots.label" default="Time Slots" /></span>
					
						<g:each in="${performerInstance.timeSlots}" var="t">
						<span class="property-value" aria-labelledby="timeSlots-label"><g:link controller="timeslot" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${performerInstance?.id}" />
					<g:link class="edit" action="edit" id="${performerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
