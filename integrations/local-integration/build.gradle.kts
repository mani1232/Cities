plugins {
    id("org.openjfx.javafxplugin") version "0.0.14"
    id("org.beryx.jlink") version "2.26.0"
}

dependencies {

}

javafx {
    version = "17"
    modules = listOf("javafx.controls")
}

jlink {
    launcher {
        name = findProperty("app-name").toString()
    }
}