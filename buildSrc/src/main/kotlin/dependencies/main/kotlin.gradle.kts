package dependencies.main

import Versions

object KotlinDependencies {
    const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect"
    const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val kotlinCoroutinesReactor = "org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.kotlinCoroutines}"
}

val implementation: Configuration by configurations
dependencies {
    implementation(platform(KotlinDependencies.kotlinBom))
    implementation(KotlinDependencies.kotlinStdLib)
    implementation(KotlinDependencies.kotlinReflect)
    implementation(KotlinDependencies.kotlinCoroutinesCore)
    implementation(KotlinDependencies.kotlinCoroutinesReactor)
}
