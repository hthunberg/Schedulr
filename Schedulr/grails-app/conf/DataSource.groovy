dataSource {
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"

	//http://jira.grails.org/browse/GRAILS-6474
	properties
	{
		maxActive = 50
		maxIdle = 25
		minIdle =1
		initialSize = 1
		minEvictableIdleTimeMillis = 60000
		timeBetweenEvictionRunsMillis = 60000
		numTestsPerEvictionRun = 3
		maxWait = 10000

		testOnBorrow = true
		testWhileIdle = true
		testOnReturn = false

		validationQuery = "SELECT 1"
	}
}

hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = true
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = "jdbc:mysql://localhost:3306/schedulr?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true"
			username = "schedulr"
			password = "schedulr"
		}
		hibernate {  show_sql = true  }
	}
	test {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = "jdbc:mysql://localhost:3306/schedulr?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true"
			username = "schedulr"
			password = "schedulr"
		}
	}
	production {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = "jdbc:mysql://localhost:3306/schedulr?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true"
			username = "schedulr"
			password = "schedulr"
		}
	}
}
