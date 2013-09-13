// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

 grails.config.locations = [ "file:${userHome}/.grails/${appName}-config.properties",
                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']


// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
		schedulr.hsaId = "tptest.callistasoftware.org"
		engagementindex.updateEngagementIndex = false
		engagementindex.endpoint.update = 'https://localhost:20000/vp/Update/1/rivtabp21'
		engagementindex.trustStore.file = '/opt/mule/certs/clienttruststore.jks'
		engagementindex.trustStore.password = '[PASSWORD]'
		engagementindex.trustStore.type = 'jks'
		engagementindex.keyStore.file = '/opt/mule/certs/client.jks'
		engagementindex.keyStore.password = '[PASSWORD]'
		engagementindex.keyStore.type = 'jks'
		engagementindex.disableCnCheck = true
		engagementindex.logicalAddress = 'HSA-ID-SLL-EI'
    }
    production {
        grails.logging.jul.usebridge = false
		schedulr.hsaId = "tptest.callistasoftware.org"
		engagementindex.updateEngagementIndex = false
		engagementindex.endpoint.update = 'https://localhost:20000/vp/Update/1/rivtabp21'
		engagementindex.trustStore.file = '/opt/mule/certs/clienttruststore.jks'
		engagementindex.trustStore.password = '[PASSWORD]'
		engagementindex.trustStore.type = 'jks'
		engagementindex.keyStore.file = '/opt/mule/certs/client.jks'
		engagementindex.keyStore.password = '[PASSWORD]'
		engagementindex.keyStore.type = 'jks'
		engagementindex.disableCnCheck = true // set to false before going to production
		engagementindex.logicalAddress = 'HSA-ID-SLL-EI'
//		engagementindex.disableCnCheck = true
		//engagementindex.logicalAddress = '5565594230'
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
	test {
//		grails.logging.jul.usebridge = true
		schedulr.hsaId = "tptest.callistasoftware.org"
		engagementindex.updateEngagementIndex = false
		engagementindex.endpoint.update = 'na'
		engagementindex.trustStore.file = 'na'
		engagementindex.trustStore.password = 'na'
		engagementindex.trustStore.type = 'na'
		engagementindex.keyStore.file = 'na'
		engagementindex.keyStore.password = 'na'
		engagementindex.keyStore.type = 'na'
		engagementindex.keyStore.password = 'na'
		engagementindex.disableCnCheck = true
		engagementindex.logicalAddress = '5565594230' // Inera's organization number
	}
}

// log4j configuration
log4j = {
    
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%d{yyMMdd HH:mm:ss,SSS} %-5p: %c -SCHDLR- %m%n')
    }

	error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
	
    debug  'org.callistasoftware.schedulr'
    
	root {
        debug()
    }
}
