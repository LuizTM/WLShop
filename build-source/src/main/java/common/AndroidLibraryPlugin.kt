package common

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the


/**
Copyright (C) 2024 LuizTM

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            the<LibraryExtension>().setupCommonConfig()
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }

    }

    private fun LibraryExtension.setupCommonConfig() {
        compileSdk = 34
        defaultConfig {
            minSdk = 25
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

}