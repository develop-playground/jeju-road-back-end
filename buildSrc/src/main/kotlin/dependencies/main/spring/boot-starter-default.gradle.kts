package dependencies.main.spring

import Versions

object BootStarterDefaultDependencies {
    const val boot = "org.springframework.boot:spring-boot-starter:${Versions.springBoot}"
    const val actuator = "org.springframework.boot:spring-boot-starter-actuator:${Versions.springBoot}"
    const val aop = "org.springframework.boot:spring-boot-starter-aop:${Versions.springBoot}"
    const val logging = "org.springframework.boot:spring-boot-starter-logging:${Versions.springBoot}"
    const val parent = "org.springframework.boot:spring-boot-starter-parent:${Versions.springBoot}"
}

val implementation: Configuration by configurations
dependencies {
    implementation(BootStarterDefaultDependencies.boot)
    implementation(BootStarterDefaultDependencies.actuator)
    implementation(BootStarterDefaultDependencies.aop)
    implementation(BootStarterDefaultDependencies.logging)
    implementation(BootStarterDefaultDependencies.parent)
}
