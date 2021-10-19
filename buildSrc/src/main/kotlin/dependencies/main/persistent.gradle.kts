package dependencies.main

import Versions


object PersistentDependencies {
    const val jooq = "org.jooq:jooq:${Versions.jooq}"
    const val postgresql = "org.postgresql:postgresql:${Versions.postgresql}"
    const val postgresqlR2dbc = "io.r2dbc:r2dbc-postgresql:${Versions.postgresqlR2dbc}"
}

val implementation: Configuration by configurations
dependencies {
    implementation(PersistentDependencies.jooq)
    implementation(PersistentDependencies.postgresql)
    implementation(PersistentDependencies.postgresqlR2dbc)
}
