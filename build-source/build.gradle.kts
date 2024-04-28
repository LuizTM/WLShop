import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

gradlePlugin {
    plugins {
        register("androidLibraryPlugin") {
            id = "dev.luiztm.plugins.android.library"
            implementationClass = "common.AndroidLibraryPlugin"
        }
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
//    compileOnly(libs.android.tools.common)
//    compileOnly(libs.firebase.crashlytics.gradlePlugin)
//    compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
//    compileOnly(libs.ksp.gradlePlugin)
//    compileOnly(libs.room.gradlePlugin)
//    implementation(libs.truth)
}