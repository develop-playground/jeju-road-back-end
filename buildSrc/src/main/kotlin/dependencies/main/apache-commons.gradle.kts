package dependencies.main

import Versions

object ApacheCommonsDependencies {
    const val commonsCodec = "commons-codec:commons-codec:${Versions.commonsCodec}"
    const val commonsDbcp2 = "org.apache.commons:commons-dbcp2:${Versions.commonsDbcp2}"
    const val commonsLang3 = "org.apache.commons:commons-lang3:${Versions.commonsLang3}"
    const val commonsPool2 = "org.apache.commons:commons-pool2:${Versions.commonsPool2}"
    const val commonsText = "org.apache.commons:commons-text:${Versions.commonsText}"
}

val implementation: Configuration by configurations
dependencies {
    implementation(ApacheCommonsDependencies.commonsCodec)
    implementation(ApacheCommonsDependencies.commonsDbcp2)
    implementation(ApacheCommonsDependencies.commonsLang3)
    implementation(ApacheCommonsDependencies.commonsPool2)
    implementation(ApacheCommonsDependencies.commonsText)
}
