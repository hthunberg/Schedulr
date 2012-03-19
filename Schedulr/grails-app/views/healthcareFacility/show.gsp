
<%@ page import="org.callistasoftware.schedulr.domain.HealthcareFacility" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'healthcareFacility.label', default: 'HealthcareFacility')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-healthcareFacility" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-healthcareFacility" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list healthcareFacility">
			
				<g:if test="${healthcareFacilityInstance?.healthcareFacility}">
				<li class="fieldcontain">
					<span id="healthcareFacility-label" class="property-label"><g:message code="healthcareFacilityId.label" default="Healthcare Facility" /></span>
					
						<span class="property-value" aria-labelledby="healthcareFacility-label"><g:fieldValue bean="${healthcareFacilityInstance}" field="healthcareFacility"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${healthcareFacilityInstance?.healthcareFacilityName}">
				<li class="fieldcontain">
					<span id="healthcareFacilityName-label" class="property-label"><g:message code="healthcareFacility.label" default="Healthcare Facility Name" /></span>
					
						<span class="property-value" aria-labelledby="healthcareFacilityName-label"><g:fieldValue bean="${healthcareFacilityInstance}" field="healthcareFacilityName"/></span>
					
				</li>
				</g:if>
			
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${healthcareFacilityInstance?.id}" />
					<g:link class="edit" action="edit" id="${healthcareFacilityInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
