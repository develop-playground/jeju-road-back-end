package dependencies.main.spring

import Versions

object BootStarterWebFluxDependencies {
    const val webflux = "org.springframework.boot:spring-boot-starter-webflux:${Versions.springBoot}"
}

val implementation: Configuration by configurations
dependencies {
    implementation(BootStarterWebFluxDependencies.webflux)
}
