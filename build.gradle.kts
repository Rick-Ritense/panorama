import org.apache.tools.ant.taskdefs.condition.Os

plugins {
    war

    kotlin("jvm") version "2.0.20"
    kotlin("plugin.spring") version "2.0.20"
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.ritense"
version = "${project.property("version")}"

apply(from = "gradle/deployment.gradle.kts")

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.10.1")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.6")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.6")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    if (Os.isFamily(Os.FAMILY_MAC)) {
        if (Os.isArch("aarch64")) {
            println("   - Applying Mac OS (aarch64) specific dependencies.")

            runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.115.Final") {
                artifact {
                    classifier = "osx-aarch_64"
                }
            }
        }
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks {
    bootRun {
        environment("SPRING_PROFILES_ACTIVE", "localhost")
    }

    bootWar.configure {
        archiveFileName.set("${project.name}-${project.property("version")}.war")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

tasks.named("war") {
    enabled = false // Disables plain WAR creation
}

tasks.named("bootWar") {
    enabled = true // Ensures only the Spring Boot WAR is built
}