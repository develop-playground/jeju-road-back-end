package dependencies.test

import Versions
import org.gradle.kotlin.dsl.dependencies

object TestContainerDependencies {
    const val postgresql = "org.testcontainers:postgresql:${Versions.testContainer}"
    const val jupiter = "org.testcontainers:junit-jupiter:${Versions.testContainer}"
}

val testCompile: Configuration by configurations
val testImplementation: Configuration by configurations
dependencies {
    testCompile(TestContainerDependencies.postgresql)
    testImplementation(TestContainerDependencies.jupiter)
}
