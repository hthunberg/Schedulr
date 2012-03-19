<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="application.name" /></title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}
            
			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="status" role="complementary">
			<h1>Application Status</h1>
			<ul>
				<li>App version: <g:meta name="app.version"/></li>
				<li>Grails version: <g:meta name="app.grails.version"/></li>
				<li>Groovy version: ${org.codehaus.groovy.runtime.InvokerHelper.getVersion()}</li>
				<li>JVM version: ${System.getProperty('java.version')}</li>
				<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
				<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
				<li>Domains: ${grailsApplication.domainClasses.size()}</li>
				<li>Services: ${grailsApplication.serviceClasses.size()}</li>
				<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
			</ul>
			<h1>Installed Plugins</h1>
			<ul>
				<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
					<li>${plugin.name} - ${plugin.version}</li>
				</g:each>
			</ul>
		</div>
		<div id="page-body" role="main">
			<h1><g:message code="application.name" /></h1>
			<p>Välkommen till tidbokningstjänsten <g:message code="application.name" />. Här kan du hantera dina vårdenheter, personal och vårdtagare. 
			   Huvudfunktionen är att skapa tidbokningar för vårdtagare hos en vårdgivare.</p>
				
			<div class="dialog" style="margin-left:20px;width:60%;" role="navigation">
        		<g:link controller="timeslot" action="list">
        			<h2><g:message code="timeSlots.label" default="Timeslots" /></h2>
        		</g:link>
        		
        		<g:link controller="timeslot" action="create">
        			<h2><g:message code="creataTimeslot.label" default="Create a timeslot" /></h2>
        		</g:link>
			</div>
			
			<div class="dialog" style="margin-left:20px;width:60%;" role="navigation">
        		<g:link controller="healthcareFacility" action="list">
        			<h2><g:message code="healthcareFacilities.label" default="Healthcare facilities" /></h2>
        		</g:link>
        		
        		<g:link controller="performer" action="list">
        			<h2><g:message code="performer.label" default="Performers" /></h2>
        		</g:link>
        		
        		<g:link controller="subjectOfCare" action="list">
        			<h2><g:message code="subjectOfCare.label" default="Subject of cares" /></h2>
        		</g:link>
			</div>
			
		</div>
	</body>
</html>
