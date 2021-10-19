package dependencies.main

import Versions

object ArrowDependencies {
    const val arrowMeta = "io.arrow-kt:arrow-meta:${Versions.arrow}"
    const val arrowStack = "io.arrow-kt:arrow-stack:${Versions.arrow}"
    const val arrowCore = "io.arrow-kt:arrow-core"
    const val arrowOptics = "io.arrow-kt:arrow-optics"
    const val arrowFxCoroutines = "io.arrow-kt:arrow-fx-coroutines"
}

val implementation: Configuration by configurations
val kapt: Configuration by configurations
dependencies {
    implementation(platform(ArrowDependencies.arrowStack))
    implementation(ArrowDependencies.arrowCore)
    implementation(ArrowDependencies.arrowFxCoroutines)
    implementation(ArrowDependencies.arrowOptics)
    kapt(ArrowDependencies.arrowMeta)
}
