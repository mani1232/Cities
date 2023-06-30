plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "cc.worldmandia"
version = findProperty("version")!!
var projectName = findProperty("app-name")


dependencies {
    implementation(project(":city-app"))
}


allprojects {

    apply(plugin = "java")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "application")

    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        // Local .jar libs
        compileOnly(fileTree("/lib") {
            include("*.jar")
        })

        compileOnly("org.projectlombok:lombok:1.18.28")
        annotationProcessor("org.projectlombok:lombok:1.18.28")

        testCompileOnly("org.projectlombok:lombok:1.18.28")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.28")
        testImplementation(platform("org.junit:junit-bom:5.9.1"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks.shadowJar {
        project.setProperty("mainClassName", "cc.worldmandia.App")
    }

    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.isIncremental = true
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.test {
        useJUnitPlatform()
    }

}