package dependencies.test

import Versions

object KotlinTestDependencies {
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
    const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
}

val testImplementation: Configuration by configurations
dependencies {
    testImplementation(KotlinTestDependencies.kotlinTest)
    testImplementation(KotlinTestDependencies.kotlinTestJunit)
}
