package dependencies.test

import Versions

object ReactorDependencies {
    const val reactorTest = "io.projectreactor:reactor-test:${Versions.reactorTest}"
}

val testImplementation: Configuration by configurations
dependencies {
    testImplementation(ReactorDependencies.reactorTest)
}
