plugins {
    alias(libs.plugins.android.library.setup)
}

android {
    namespace = "dev.luiztm.ui_ds"
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}