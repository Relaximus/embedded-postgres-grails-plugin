package embedded.postgres

import embedded.postgres.utils.DriverUtils
import groovy.sql.Sql
import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

class MultipleDataSourcesSpec extends Specification implements GrailsUnitTest {

    Closure doWithConfig() {{ config ->
        config.dataSource.url='jdbc:h2:mem:h2bd'
        config.dataSources.postgres.embeddedPostgres=true
    }}

    Set<String> getIncludePlugins() {
        ["dataSource",'embeddedPostgres'].toSet()
    }

    def setup() {
        DriverUtils.refreshPostgres()
        DriverUtils.refreshH2()
    }

    def "Embedded Postgres with custom options"() {
        when: // H2 is on
        def dataSource = applicationContext.getBean('dataSource')
        Sql sql = new Sql(dataSource)
        def dbName = sql.rows('SELECT DATABASE() as name FROM DUAL').first().getProperty('name')

        then:
        dbName == 'H2BD'

        when: // And Postgres is on
        dataSource = applicationContext.getBean('dataSource_postgres')
        sql = new Sql(dataSource)
        String version = sql.rows('SELECT version() as version').first().getProperty('version')

        then:
        version.startsWith('PostgreSQL')
    }
}
