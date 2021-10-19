package dependencies.test.spring

import Versions

object FrameworkRestdocsDependencies {
    const val webTestClient = "org.springframework.restdocs:spring-restdocs-webtestclient:${Versions.restDoc}"
}

val testImplementation: Configuration by configurations
dependencies {
    testImplementation(FrameworkRestdocsDependencies.webTestClient)
}
