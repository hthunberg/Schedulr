<%@ page import="org.callistasoftware.schedulr.domain.SubjectOfCare" %>



<div class="fieldcontain ${hasErrors(bean: subjectOfCareInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${subjectOfCareInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: subjectOfCareInstance, field: 'middleName', 'error')} ">
	<label for="middleName">
		<g:message code="middleName.label" default="Middle Name" />
		
	</label>
	<g:textField name="middleName" value="${subjectOfCareInstance?.middleName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: subjectOfCareInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${subjectOfCareInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: subjectOfCareInstance, field: 'subjectOfCareId', 'error')} required">
	<label for="subjectOfCareId">
		<g:message code="subjectOfCareId.label" default="Subject Of Care Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="subjectOfCareId" required="" value="${subjectOfCareInstance?.subjectOfCareId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: subjectOfCareInstance, field: 'homeAddress', 'error')} required">
	<label for="homeAddress">
		<g:message code="homeAddress.label" default="Home Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="homeAddress" required="" value="${subjectOfCareInstance?.homeAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: subjectOfCareInstance, field: 'coAddress', 'error')} ">
	<label for="coAddress">
		<g:message code="coAddress.label" default="Co Address" />
		
	</label>
	<g:textField name="coAddress" value="${subjectOfCareInstance?.coAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: subjectOfCareInstance, field: 'phone', 'error')} required">
	<label for="phone">
		<g:message code="phone.label" default="Phone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="phone" required="" value="${subjectOfCareInstance?.phone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: subjectOfCareInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${subjectOfCareInstance?.email}"/>
</div>

