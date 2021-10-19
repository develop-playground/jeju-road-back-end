package dependencies.test

import Versions

object RestDocsDependencies {
    const val apiSpecWebTestClient = "com.epages:restdocs-api-spec-webtestclient:${Versions.apiSpecWebTestClient}"
}

val testImplementation: Configuration by configurations
dependencies {
    testImplementation(RestDocsDependencies.apiSpecWebTestClient)
}
