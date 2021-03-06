
<%@ page import="org.callistasoftware.schedulr.domain.Performer" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'performer.label', default: 'Performer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-performer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-performer" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						
						<g:sortableColumn property="title" title="${message(code: 'title.label', default: 'Title')}" />
						
						<g:sortableColumn property="firstName" title="${message(code: 'firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="performerId" title="${message(code: 'performerId.label', default: 'Performer ID')}" />
						
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${performerInstanceList}" status="i" var="performerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: performerInstance, field: "title")}</td>
					
						<td>${fieldValue(bean: performerInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: performerInstance, field: "lastName")}</td>
					
						<td><g:link action="show" id="${performerInstance.id}">${fieldValue(bean: performerInstance, field: "performerId")}</g:link></td>
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${performerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
