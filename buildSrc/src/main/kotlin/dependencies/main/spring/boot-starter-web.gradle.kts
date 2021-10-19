package dependencies.main.spring

import Versions

object BootStarterWebDependencies {
    const val web = "org.springframework.boot:spring-boot-starter-web:${Versions.springBoot}"
    const val undertow = "org.springframework.boot:spring-boot-starter-undertow:${Versions.springBoot}"
}

val implementation: Configuration by configurations
dependencies {
    implementation(BootStarterWebDependencies.web) {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation(BootStarterWebDependencies.undertow) {
        exclude(group = "io.undertow", module = "undertow-websockets-jsr")
    }
}
