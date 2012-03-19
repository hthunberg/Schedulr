
<%@ page import="org.callistasoftware.schedulr.domain.Timeslot" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'timeslot.label', default: 'Timeslot')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-timeslot" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-timeslot" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="startTimeInclusive" title="${message(code: 'startTimeInclusive.label', default: 'Start Time')}" />
					
						<g:sortableColumn property="endTimeExclusive" title="${message(code: 'endTimeExclusive.label', default: 'End Time')}" />
								
						<g:sortableColumn property="caretype" title="${message(code: 'caretype.label', default: 'Care Type')}" />
						
						<g:sortableColumn property="performer" title="${message(code: 'performer.label', default: 'Performer')}" />
						
						<g:sortableColumn property="subjectOfCare" title="${message(code: 'subjectOfCare.label', default: 'Subject of care')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${timeslotInstanceList}" status="i" var="timeslotInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${timeslotInstance.id}"><g:formatDate date="${timeslotInstance.startTimeInclusive}" type="datetime" style="SHORT" timeStyle="SHORT"/></g:link></td>
					
						<td><g:formatDate date="${timeslotInstance.endTimeExclusive}" type="datetime" style="SHORT" timeStyle="SHORT"/></td>
					
						<td>${fieldValue(bean: timeslotInstance, field: "caretype")}</td>
						
						<td><g:link controller="subjectOfCare" action="show" id="${timeslotInstance.subjectOfCare.id}">${fieldValue(bean: timeslotInstance, field: "performer")}</g:link></td>
						
						<td><g:link controller="subjectOfCare" action="show" id="${timeslotInstance.subjectOfCare.id}">${fieldValue(bean: timeslotInstance, field: "subjectOfCare")}</g:link></td>
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${timeslotInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
