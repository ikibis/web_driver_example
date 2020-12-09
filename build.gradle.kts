import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "me.ilya"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8")
    testImplementation(group = "io.kotlintest", name = "kotlintest-runner-junit5", version = "3.1.8")
    testImplementation(group = "org.seleniumhq.selenium", name = "selenium-java", version = "3.14.0")
    testImplementation(group = "org.seleniumhq.selenium", name = "selenium-chrome-driver", version = "3.14.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
