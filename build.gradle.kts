import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.9.10"
	kotlin("plugin.spring") version "1.9.10"
}

group = "com.railly_linker"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_20
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation("de.codecentric:spring-boot-admin-starter-server:3.1.6")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// (Spring Starter Web)
	// : 스프링 부트 웹 개발
	implementation("org.springframework.boot:spring-boot-starter-web")

	// (Spring Security)
	// : 스프링 부트 보안
	implementation("org.springframework.boot:spring-boot-starter-security")

	// (Spring mail)
	// : 스프링 부트 메일 발송
	implementation("org.springframework.boot:spring-boot-starter-mail")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "20"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
