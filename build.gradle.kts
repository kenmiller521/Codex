// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra["kotlin_version"] = "1.9.0"
    extra["room_version"] = "2.3.0"
    extra["coil_version"] = "1.3.2"
    extra["nav_version"] = "2.3.5"
    extra["lifecycle_version"] = "2.3.1"
    extra["hilt_version"] = "2.38.1"
    extra["compose_version"] = "1.3.0"
    extra["accompanist_version"] = "0.16.0"

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath ("com.android.tools.build:gradle:7.3.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.38.1")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}
