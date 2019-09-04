import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.bundling.BootWar

plugins {
	id("org.springframework.boot") version "2.1.7.RELEASE"
	// id("org.springframework.boot") version "2.2.0.M5"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	war
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	kotlin("plugin.jpa") version "1.3.50"
}

group = "my.examples"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_12

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

repositories {
	mavenCentral()
	maven("https://repo.spring.io/milestone")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation(kotlin("script-runtime"))
	implementation(kotlin("compiler"))
	implementation(kotlin("scripting-compiler"))
	implementation(kotlin("script-util"))
	// implementation(kotlin("scripting-jsr223-embeddable"))
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	runtimeOnly(project(":app-react-components"))
	/*runtimeOnly(files("$buildDir/classes") {
		builtBy("compile")
	})*/
	// implementation(project(":app-react-components"))
	// runtimeOnly("org.springframework.boot:spring-boot-starter-tomcat")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "12"
	}
}

tasks.withType<BootWar> {
	requiresUnpack("**/kotlin-*.jar")
	requiresUnpack("**/kotlinx-*.jar")
	requiresUnpack("**/*.kotlin_module")
	requiresUnpack("**/*.kotlin-module")
}

tasks.withType<BootJar> {
	requiresUnpack("**/kotlin-*.jar")
	requiresUnpack("**/kotlinx-*.jar")
	requiresUnpack("**/*.kotlin_module")
	requiresUnpack("**/*.kotlin-module")
}
