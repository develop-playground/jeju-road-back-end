package dependencies.test

import Versions

object AssertDependencies {
    const val assertj = "org.assertj:assertj-core:${Versions.assertj}"
}

val testImplementation: Configuration by configurations
dependencies {
    testImplementation(AssertDependencies.assertj)
}
