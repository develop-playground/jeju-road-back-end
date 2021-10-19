package dependencies.test.spring

import Versions

object kotestDependencies {
    const val kotestExtensionsSpring = "io.kotest:kotest-extensions-spring:${Versions.kotestExtensionsSpring}"
}

val testImplementation: Configuration by configurations
dependencies {
    testImplementation(kotestDependencies.kotestExtensionsSpring)
}
