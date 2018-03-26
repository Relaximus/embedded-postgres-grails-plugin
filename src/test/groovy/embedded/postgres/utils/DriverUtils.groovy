package embedded.postgres.utils

import org.postgresql.Driver as PostgresDriver
import org.h2.Driver as H2Driver


class DriverUtils {
    static def refreshPostgres(){
        if(PostgresDriver.isRegistered()) PostgresDriver.deregister()
        PostgresDriver.register()
    }

    static def refreshH2(){
        H2Driver.unload()
        H2Driver.load()
    }
}
