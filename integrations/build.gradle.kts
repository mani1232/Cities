allprojects {

    dependencies {
        implementation(project(":city-api", configuration = "shadow"))
    }
}