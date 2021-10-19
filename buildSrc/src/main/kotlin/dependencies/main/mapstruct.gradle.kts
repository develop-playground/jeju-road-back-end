package dependencies.main

import Versions

object MapStructDependencies {
    const val mapStruct = "org.mapstruct:mapstruct:${Versions.mapstruct}"
    const val mapStructProcessor = "org.mapstruct:mapstruct-processor:${Versions.mapstruct}"
}

val implementation: Configuration by configurations
val kapt by configurations
dependencies {
    implementation(MapStructDependencies.mapStruct)
    kapt(MapStructDependencies.mapStructProcessor)
}
