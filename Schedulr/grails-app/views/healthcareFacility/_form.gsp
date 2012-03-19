<%@ page import="org.callistasoftware.schedulr.domain.HealthcareFacility" %>



<div class="fieldcontain ${hasErrors(bean: healthcareFacilityInstance, field: 'healthcareFacility', 'error')} required">
	<label for="healthcareFacility">
		<g:message code="healthcareFacilityId.label" default="Healthcare Facility" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="healthcareFacility" required="" value="${healthcareFacilityInstance?.healthcareFacility}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: healthcareFacilityInstance, field: 'healthcareFacilityName', 'error')} required">
	<label for="healthcareFacilityName">
		<g:message code="healthcareFacility.label" default="Healthcare Facility Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="healthcareFacilityName" required="" value="${healthcareFacilityInstance?.healthcareFacilityName}"/>
</div>

