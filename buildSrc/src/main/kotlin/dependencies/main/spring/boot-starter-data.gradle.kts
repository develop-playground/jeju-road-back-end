package dependencies.main.spring

import Versions

object BootStarterWebDataDependencies {
    const val r2dbc = "org.springframework.boot:spring-boot-starter-data-r2dbc:${Versions.springBoot}"
}

val implementation: Configuration by configurations
dependencies {
    implementation(BootStarterWebDataDependencies.r2dbc)
}
