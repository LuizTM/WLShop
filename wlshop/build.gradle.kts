plugins {
    alias(libs.plugins.android.library.setup)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "dev.luiztm.wlshop"

    //thus ensuring they don't conflict with other libraries or resources from libraries
    resourcePrefix("wlshop")

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {

    implementation(project(":shared:sa-analytics"))
    implementation(project(":shared:sa-network"))
    implementation(project(":shared:sa-data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.viewModel.ktx)
    implementation(libs.material)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit4)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.espresso.core)
    testImplementation(libs.androidx.archcore.testing)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.core.ktx)
//    testImplementation(libs.hamcrest)
    testImplementation(libs.google.truth)
    testImplementation(libs.androidx.test.ext)
    testImplementation(libs.androidx.test.rules)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.test.runner)
    testImplementation(libs.io.mockk)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}