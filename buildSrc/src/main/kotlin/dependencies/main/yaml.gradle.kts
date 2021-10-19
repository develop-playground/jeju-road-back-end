package dependencies.main

import Versions

object YamlDependencies {
    const val resourceBundle = "dev.akkinoc.util:yaml-resource-bundle:${Versions.resourceBundle}"
}

val implementation: Configuration by configurations
dependencies {
    implementation(YamlDependencies.resourceBundle)
}
