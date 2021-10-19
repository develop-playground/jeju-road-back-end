package dependencies.test

import Versions

object JunitDependencies {
    const val jupiter = "org.junit.jupiter:junit-jupiter:${Versions.junit}"
}

val testImplementation: Configuration by configurations
dependencies {
    testImplementation(JunitDependencies.jupiter)
}
