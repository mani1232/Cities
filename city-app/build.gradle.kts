dependencies {
    implementation(project(":city-api", configuration = "shadow"))
    implementation(project(":integrations:local-integration", configuration = "shadow"))
    implementation(project(mapOf("path" to ":city-api")))
    implementation(project(mapOf("path" to ":integrations:local-integration")))
    implementation("org.testng:testng:7.1.0")
}