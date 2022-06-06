val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val hikari_cp: String by project
val postgres_ql: String by project

plugins {
    application
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
}

group = "com.biologyapp_backend"
version = "0.0.1"
application {
    mainClass.set("com.biologyapp_backend.ApplicationKt")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {

    //Core
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")

    //Serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    //DataBase
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.postgresql:postgresql:$postgres_ql")
    implementation("com.zaxxer:HikariCP:$hikari_cp")

    // Библиотеки для ?
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-cio-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    //Библиотека для ?
    implementation("io.ktor:ktor-server-netty:$ktor_version")

}

//Таска для деплоя сервера
tasks.create("stage") {
    dependsOn("installDist")
}
