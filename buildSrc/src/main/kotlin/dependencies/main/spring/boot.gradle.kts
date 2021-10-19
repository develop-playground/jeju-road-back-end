package dependencies.main.spring

import Versions

object BootDependencies {
    const val boot = "org.springframework.boot:spring-boot:${Versions.springBoot}"
    const val autoconfigure = "org.springframework.boot:spring-boot-autoconfigure:${Versions.springBoot}"
    const val configurationProcessor =
        "org.springframework.boot:spring-boot-configuration-processor:${Versions.springBoot}"
    const val devtools = "org.springframework.boot:spring-boot-devtools:${Versions.springBoot}"
}

val implementation: Configuration by configurations
val runtimeOnly: Configuration by configurations
dependencies {
    implementation(BootDependencies.boot)
    implementation(BootDependencies.autoconfigure)
    implementation(BootDependencies.configurationProcessor)

    runtimeOnly(BootDependencies.devtools)
}
