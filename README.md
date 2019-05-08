# embedded-postgres-grails-plugin 
[![Build Status](https://travis-ci.org/Relaximus/embedded-postgres-grails-plugin.svg?branch=master)](https://travis-ci.org/Relaximus/embedded-postgres-grails-plugin)
[ ![Download](https://api.bintray.com/packages/relaximus/plugins/embedded-postgres/images/download.svg?version=1.0) ](https://bintray.com/relaximus/plugins/embedded-postgres/1.0/link)

Plugin replaces default embedded H2 datasource in awesome [Grails](http://grails.org) framework 
to Postgres. Plugin uses already existing solution [otj-pg-embedded](https://github.com/opentable/otj-pg-embedded). 

This plugin is not for production use, the main idea to have
your development and u-test environment similar to the standalone production Postgres (of course, if you use this DB in the project). 

## Getting Started

To get started with the minimum of configuration start from the following:
Add to your ***build.gradle***
```groovy
repositories {
    maven {
        url  "https://dl.bintray.com/grails/plugins" 
    }
}
```
```groovy
compile 'org.grails.plugins:embedded-postgres:1.1.2'
```
In your ***application.yml*** change the dataSource settings to the next one:
```yaml
dataSource:
    embeddedPostgres: true
```

### Prerequisites

Plugin configured to work with Grails **3.0.0** or higher, but theoretically can be used even for 
older version of it. But this was not checked properly, so contributing is welcome.

### Configuration

Plugin uses next parameters in DataSource section:

|Parameter name|Description|Default value|
|--------------|-----------|-------------|
| *embeddedPostgres* | Enabling of the plugin, main switcher. | false |
| *embeddedPort* | You can define the particular port, which will be used by Postgres instance | random free port |
| *url* | You can specify the url with any additional parameters, which Postgres understands | jdbc:postgresql://localhost:<embeddedPort>/postgres |
| *username* || postgres |
| *password* || postgres |

All other parameters common for the Grails Datasource configuration section are being used by grails dataSource plugin.
For example, you can set
```yaml
dataSource:
    pooled: true
    embeddedPostgres: true
``` 
In this case embedded Postgres will be created with connection pool in front of it.

## License

[Apache License, Version 2.0](https://opensource.org/licenses/apache2.0.php)

## Acknowledgments

* Great respect to the [otj-pg-embedded](https://github.com/opentable/otj-pg-embedded) for Embedded Postgres implementation.
