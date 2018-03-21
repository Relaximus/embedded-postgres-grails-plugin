package embedded.postgres

import grails.plugins.Plugin

class EmbeddedPostgresGrailsPlugin extends Plugin {
    def grailsVersion   = "3.2.10 > *"
    def title           = "Embedded Postgres"
    def author          = "Alexey Chentsov"
    def authorEmail     = "alche8411@gmail.com"
    def description     = 'Plugin starts local instance of postgres and after program termination clears temporary data. Simulates embedded postgres.'
    def documentation   = "https://github.com/Relaximus/embedded-postgres-grails-plugin"
    def license         = "APACHE"
    def organization    = [name: "Alexey Chentsov", url: "https://github.com/Relaximus"]
    def issueManagement = [ system: "GITHUB", url: "https://github.com/Relaximus/embedded-postgres-grails-plugin/issues" ]
    def scm             = [ url: "https://github.com/Relaximus/embedded-postgres-grails-plugin" ]
    def pluginExcludes  = []
    def developers      = [ [name: 'Alexey Chentsov'] ]
    def dependsOn = [datasource: '*']

    Closure doWithSpring() { {->
        def config = grailsApplication.config
        if (config.dataSource.embeddedPostgres) {
            embeddedPostgresFactory(EmbeddedPostgresFactory){
                port = config.dataSource?.embeddedPort
            }
            embeddedPostgres(embeddedPostgresFactory: "getDataBase")
            getBeanDefinition('dataSource').setDependsOn('embeddedPostgres')
        }
        }
    }
}
