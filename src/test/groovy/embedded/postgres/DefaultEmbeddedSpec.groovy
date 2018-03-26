package embedded.postgres

import embedded.postgres.utils.DriverUtils
import groovy.sql.Sql
import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

class DefaultEmbeddedSpec extends Specification implements GrailsUnitTest {

    Closure doWithConfig() {{ config ->
        config.dataSource.with {
            url='jdbc:h2:mem:h2bd'
        }
    }}

    Set<String> getIncludePlugins() {
        ["dataSource"].toSet()
    }

    def setup() {
        DriverUtils.refreshH2()
    }

    def "Standard embedded H2 with default options"() {
        when:
        def dataSource = applicationContext.getBean('dataSource')
        Sql sql = new Sql(dataSource)
        def dbName = sql.rows('SELECT DATABASE() as name FROM DUAL').first().getProperty('name')

        then:
        dbName == 'H2BD'
    }
}
