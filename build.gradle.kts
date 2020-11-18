plugins {
    kotlin("jvm") version "1.3.72"
    kotlin("kapt") version "1.3.72"
    kotlin("plugin.allopen") version "1.3.72"
    id("io.quarkus")
}

repositories {
    mavenLocal()
    mavenCentral()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-hibernate-orm")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy")
    implementation("io.quarkus:quarkus-jdbc-h2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")

    // QueryDSL
    implementation("com.querydsl:querydsl-jpa:4.4.0")
    kapt("com.querydsl:querydsl-apt:4.4.0:jpa")

    // Tests
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("com.github.database-rider:rider-cdi:1.16.0"){
        exclude(group="org.apache.poi")
    }
}

group = "com.example"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

quarkus {
    setOutputDirectory("$projectDir/build/classes/kotlin/main")
}

tasks.withType<io.quarkus.gradle.tasks.QuarkusDev> {
    setSourceDir("$projectDir/src/main/kotlin")
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}
