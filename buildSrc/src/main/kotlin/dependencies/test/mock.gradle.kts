package dependencies.test

import Versions

object MockDependencies {
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoJupiter = "org.mockito:mockito-junit-jupiter:${Versions.mockito}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockito}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
}

val testImplementation: Configuration by configurations
dependencies {
    testImplementation(MockDependencies.mockitoCore)
    testImplementation(MockDependencies.mockitoJupiter)
    testImplementation(MockDependencies.mockitoInline)
    testImplementation(MockDependencies.mockitoKotlin)
}
