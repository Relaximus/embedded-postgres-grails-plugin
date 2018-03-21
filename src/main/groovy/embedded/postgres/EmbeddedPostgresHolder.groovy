package embedded.postgres

import com.opentable.db.postgres.embedded.EmbeddedPostgres
import groovy.util.logging.Slf4j

import javax.annotation.PreDestroy

@Slf4j
class EmbeddedPostgresHolder {
    private EmbeddedPostgres db

    EmbeddedPostgresHolder(EmbeddedPostgres db) {
        this.db = db
    }

    def getPort(){
        db.port
    }

    @PreDestroy
    def close() {
        log.warn "Stopping ${db.toString()}"
        db.close()
    }
}

