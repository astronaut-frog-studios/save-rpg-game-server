import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    jacoco
}

group = "com.astrofrog"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

jacoco {
    toolVersion = "0.8.7"
    reportsDirectory.set(layout.buildDirectory.dir("jacocoReports"))
}

val excludePackages: Iterable<String> = listOf(
    "**/com/astrofrog/saverpggameserver/application/**",
    "**/com/astrofrog/saverpggameserver/domain/entities/**",
)

extra["excludePackages"] = excludePackages

tasks.jacocoTestReport {
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }

    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            exclude(excludePackages)
        },
    )
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = 0.7.toBigDecimal()
            }
        }
    }

    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            exclude(excludePackages)
        },
    )

    mustRunAfter(tasks["jacocoTestReport"])
}

dependencies {
    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Web
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.12")

    // Database
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    // Log
    implementation("org.apache.logging.log4j:log4j:2.20.0")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
