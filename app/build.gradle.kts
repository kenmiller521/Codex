plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

val kotlin_version = rootProject.extra["kotlin_version"]
val room_version = rootProject.extra["room_version"]
val coil_version = rootProject.extra["coil_version"]
val lifecycle_version = rootProject.extra["lifecycle_version"]
val hilt_version = rootProject.extra["hilt_version"]
val nav_version = rootProject.extra["nav_version"]
val compose_version = rootProject.extra["compose_version"]
val accompanist_version = rootProject.extra["accompanist_version"]

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.zil.codex"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            isDebuggable = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false
        }

        debug {
            isDebuggable = true
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isMinifyEnabled = false

            if (project.hasProperty("devBuild")) {
                aaptOptions.cruncherEnabled = false
            }
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "$compose_version"
    }
    packagingOptions {
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("LICENSE.txt")
        resources.excludes.add("META-INF/rxjava.properties")
        resources.excludes.add("ETA-INF/*.kotlin_module")
    }
}

dependencies {
    implementation ("androidx.core:core-ktx:$kotlin_version")
    implementation ("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.activity:activity-ktx:1.5.1")
    implementation("androidx.fragment:fragment-ktx:1.5.1")
    implementation ("com.google.android.material:material:1.7.0")
    implementation ("androidx.compose.ui:ui:$compose_version")
    implementation ("androidx.compose.material:material:$compose_version")
    implementation ("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.activity:activity-compose:1.7.0-alpha02")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation ("androidx.compose.ui:ui-tooling:$compose_version")

    /** Coil */
    implementation("io.coil-kt:coil:$coil_version")
    implementation("io.coil-kt:coil-compose:$coil_version")

    /** Room */
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor ("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    /** Kotlin Coroutine */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    /** Navigation */
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation("androidx.navigation:navigation-compose:2.6.0-alpha03")

    /** Hilt */
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    /** OkHttp */
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0") {
        exclude("com.squareup.okhttp3", "okhttp")
    }

    /** Retrofit */
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
}

kapt {
    useBuildCache = true
    correctErrorTypes = true
}
