package embedded.postgres

import embedded.postgres.utils.DriverUtils
import groovy.sql.Sql
import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

import javax.sql.DataSource

class DefaultEnabledEmbeddedPostgresSpec extends Specification implements GrailsUnitTest {

    Closure doWithConfig() {{ config ->
        config.dataSource.with {
            embeddedPostgres=true
        }
    }}

    Set<String> getIncludePlugins() {
        ["dataSource",'embeddedPostgres'].toSet()
    }

    def setup() {
        DriverUtils.refreshPostgres()
    }

    def "Embedded Postgres with custom options"() {
        when:

        DataSource dataSource = applicationContext.getBean('dataSource')
        Sql sql = new Sql(dataSource)
        String version = sql.rows('SELECT version() as version').first().getProperty('version')

        then:
        version.startsWith('PostgreSQL')
    }
}
