package dependencies.main

import Versions

object LoggerDependencies {
    const val logbackCore = "ch.qos.logback:logback-core:${Versions.logback}"
    const val logbackClassic = "ch.qos.logback:logback-classic:${Versions.logback}"

    const val slf4jApi = "org.slf4j:slf4j-api:${Versions.slf4j}"
    const val jclOverSlf4j = "org.slf4j:jcl-over-slf4j:${Versions.slf4j}"
    const val julToSlf4j = "org.slf4j:jul-to-slf4j:${Versions.slf4j}"
    const val log4jOverSlf4j = "org.slf4j:log4j-over-slf4j:${Versions.slf4j}"
}

val implementation by configurations
dependencies {
    implementation(LoggerDependencies.logbackCore)
    implementation(LoggerDependencies.logbackClassic)

    implementation(LoggerDependencies.slf4jApi)
    implementation(LoggerDependencies.jclOverSlf4j)
    implementation(LoggerDependencies.julToSlf4j)
    implementation(LoggerDependencies.log4jOverSlf4j)
}
