
<%@ page import="org.callistasoftware.schedulr.domain.SubjectOfCare" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'subjectOfCare.label', default: 'SubjectOfCare')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-subjectOfCare" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-subjectOfCare" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="subjectOfCareId" title="${message(code: 'subjectOfCareId.label', default: 'Subject Of Care Id')}" />
						
						<g:sortableColumn property="firstName" title="${message(code: 'firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="phone" title="${message(code: 'phone.label', default: 'Phone')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'email.label', default: 'Email')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${subjectOfCareInstanceList}" status="i" var="subjectOfCareInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${subjectOfCareInstance.id}">${fieldValue(bean: subjectOfCareInstance, field: "subjectOfCareId")}</g:link></td>
					
						<td>${fieldValue(bean: subjectOfCareInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: subjectOfCareInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: subjectOfCareInstance, field: "phone")}</td>
					
						<td>${fieldValue(bean: subjectOfCareInstance, field: "email")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${subjectOfCareInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
