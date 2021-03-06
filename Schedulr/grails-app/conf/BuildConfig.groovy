grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
		mavenLocal()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        runtime 'mysql:mysql-connector-java:5.1.16'
		compile 'org.apache.cxf:cxf-rt-frontend-jaxrs:2.5.2'
		compile 'org.apache.cxf:cxf-rt-frontend-jaxws:2.5.2'
		compile 'org.apache.cxf:cxf-rt-transports-http:2.5.2'
		compile 'se.rivta.tools:crm-scheduling-schemas:1.1.0'
		compile 'se.riv.itintegration.monitoring:itintegration-monitoring-schemas:1.0.0'
		compile 'se.rivta.tools:itintegration-engagementindex-schemas:1'
		
    }

    plugins {
        //runtime ":hibernate:$grailsVersion" // obsoleted as of grails 2.3.5 -> http://grails.org/2.3.5+Release+Notes
		runtime ':hibernate:3.6.10.7'
        runtime ":jquery:1.7.1"
        runtime ":resources:1.1.6"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        //build ":tomcat:$grailsVersion" // obsoleted as of grails 2.3.5 -> http://grails.org/2.3.5+Release+Notes
		build ":tomcat:7.0.50"
    }
}
