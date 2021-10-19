package conventions.spring

import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // --# Gradle
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")

    // --# Project
    id("dependencies.main.kotlin")
    id("dependencies.main.apache-commons")
    id("dependencies.main.arrow")
    id("dependencies.main.configure")
    id("dependencies.main.jackson")
    id("dependencies.main.mapstruct")
    id("dependencies.main.logger")
    id("dependencies.main.persistent")
    id("dependencies.main.yaml")

    id("dependencies.main.spring.boot")
    id("dependencies.main.spring.boot-starter-data")
    id("dependencies.main.spring.boot-starter-default")
    id("dependencies.main.spring.boot-starter-webflux")

    id("dependencies.test.assert")
    id("dependencies.test.junit")
    id("dependencies.test.kotest")
    id("dependencies.test.mock")
    id("dependencies.test.reactor")
    id("dependencies.test.restdocs")
    id("dependencies.test.test-container")
    id("dependencies.test.spring.boot-starter-default")
    id("dependencies.test.spring.framework-restdocs")
    id("dependencies.test.spring.kotest")
}

repositories {
    google()
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(FAILED, PASSED, SKIPPED)
    }
}

tasks.withType<Wrapper> {
    gradleVersion = Versions.gradle
    distributionType = Wrapper.DistributionType.BIN
}
