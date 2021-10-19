package dependencies.test

import Versions

object KotestDependencies {
    const val kotestAssertionsCoreJvm = "io.kotest:kotest-assertions-core-jvm:${Versions.kotest}"
    const val kotestPropertyJvm = "io.kotest:kotest-property-jvm:${Versions.kotest}"
    const val kotestRunnerJunit5Jvm = "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}"
}

val testImplementation: Configuration by configurations
dependencies {
    testImplementation(KotestDependencies.kotestAssertionsCoreJvm)
    testImplementation(KotestDependencies.kotestPropertyJvm)
    testImplementation(KotestDependencies.kotestRunnerJunit5Jvm)
}
