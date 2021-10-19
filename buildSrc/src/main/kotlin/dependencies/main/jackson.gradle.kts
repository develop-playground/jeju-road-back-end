package dependencies.main

import Versions

object JacksonDependencies {
    const val jacksonKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}"
}

val implementation by configurations
dependencies {
    implementation(JacksonDependencies.jacksonKotlin)
}
