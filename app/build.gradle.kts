plugins {
    // --#Gradle
    id("org.jlleitschuh.gradle.ktlint")

    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.jetbrains.kotlin.plugin.spring")

    id("org.asciidoctor.jvm.convert")
    id("com.epages.restdocs-api-spec")

    // --#Project
    id("conventions.spring.kotlin-application")
}

/** Spring RestDocs 설정

val snippetsDir = file("build/generated-snippets").also {
    extra["snippetsDir"] = it
}

tasks.test {
    outputs.dir(snippetsDir)
}

tasks.asciidoctor {
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
    from(file("build/docs/asciidoc/"))
    into(file("src/main/resources/static/docs/"))
}

tasks.register("copyDocument", Copy::class) {
    dependsOn(tasks.bootJar)
    from(file("build/docs/asciidoc/"))
    into(file("src/main/resources/static/docs/"))
}

tasks.build {
    dependsOn(tasks.getByName("copyDocument"))
}

configure<com.epages.restdocs.apispec.gradle.OpenApi3Extension> {
    setServer("http://localhost:8080")
    title = "Hello, Spring Api"
    description = "Hello, Spring description"
    version = "2021.30.0"
    format = "yaml"
    separatePublicApi = true
    outputFileNamePrefix = "my-api-spec"
}

**/
