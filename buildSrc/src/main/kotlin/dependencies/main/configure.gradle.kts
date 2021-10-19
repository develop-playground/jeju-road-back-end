package dependencies.main

import Versions

object ConfigureDependencies {
    const val typesafeConfig = "com.typesafe:config:${Versions.typesafeConfig}"
}

val implementation: Configuration by configurations
dependencies {
    implementation(ConfigureDependencies.typesafeConfig)
}
