val logback_version: String by project
val ktor_version: String by project
val kotlin_version: String by project
val exposed_version: String = "0.10.4"
val h2_version:String = "1.4.197"
val spek_version = "2.0.2"

plugins {
    application
    kotlin("jvm") version "1.3.30"
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

group = "linkeeper"
version = "0.0.1-SNAPSHOT"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven {
        url = uri("https://dl.bintray.com/kotlin/exposed")
    }
    maven {
        url = uri("https://dl.bintray.com/spekframework/spek-dev/")
    }
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    compile("org.jetbrains.exposed:exposed:$exposed_version")
    compile("io.ktor:ktor-server-netty:$ktor_version")
    compile("ch.qos.logback:logback-classic:$logback_version")
    compile("io.ktor:ktor-gson:$ktor_version")
    compile("com.h2database:h2:$h2_version")
    testCompile("io.ktor:ktor-server-tests:$ktor_version")
    testCompile("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
    testCompile("org.spekframework.spek2:spek-runner-junit5:$spek_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }
}

tasks.test {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}