package conventions

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
    id("dependencies.main.logger")

    id("dependencies.test.assert")
    id("dependencies.test.junit")
    id("dependencies.test.kotest")
    id("dependencies.test.mock")
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
