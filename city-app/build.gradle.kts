dependencies {
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.4.8")

    implementation(project(":city-api", configuration = "shadow"))
    implementation(project(":integrations:local-integration", configuration = "shadow"))
}