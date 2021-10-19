package dependencies.test.spring

import Versions

object BootStarterDefaultDependencies {
    const val test = "org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}"
}

val testImplementation: Configuration by configurations
dependencies {
    testImplementation(BootStarterDefaultDependencies.test) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}
