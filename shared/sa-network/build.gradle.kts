plugins {
    alias(libs.plugins.android.library.setup)
}

android {
    namespace = "dev.luiztm.sa_network"

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
}