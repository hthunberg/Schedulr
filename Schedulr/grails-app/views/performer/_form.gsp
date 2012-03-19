<%@ page import="org.callistasoftware.schedulr.domain.Performer" %>

<div class="fieldcontain ${hasErrors(bean: performerInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${performerInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: performerInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${performerInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: performerInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${performerInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: performerInstance, field: 'performerId', 'error')} required">
	<label for="performer">
		<g:message code="performerId.label" default="Performer ID" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="performerId" required="" value="${performerInstance?.performerId}"/>
</div>




