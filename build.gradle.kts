import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id( "com.github.ben-manes.versions") version "0.39.0"
	id("org.springframework.boot") version "2.5.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.10"
	kotlin("plugin.spring") version "1.5.10"
}

group = "uk.gov.dwp.dataworks"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation(platform("software.amazon.awssdk:bom:2.16.79"))
	implementation("software.amazon.awssdk:sqs")

	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
	testImplementation("io.kotest:kotest-assertions-core-jvm:4.6.0")
	testImplementation("io.kotest:kotest-assertions-json-jvm:4.6.0")
	testImplementation("io.kotest:kotest-property-jvm:4.6.0")
	testImplementation("io.kotest:kotest-runner-junit5-jvm:4.6.0")
	testImplementation("io.kotest:kotest-extensions-spring:4.6.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
