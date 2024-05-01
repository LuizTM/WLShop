package dev.luiztm.sa_network

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


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
fun AppCompatActivity.injectSANetwork(): Lazy<SANetwork> {
    return lazy { provideSANetwork() }
}
fun Fragment.injectSANetwork(): Lazy<SANetwork?> {
    val context = checkNotNull(context) { "context reference must not be null" }
    return lazy { context.provideSANetwork() }
}

fun Context.provideSANetwork() =
    try {
        applicationContext as SANetwork
    } catch (ex: ClassCastException) {
        throw ClassCastException("The application must be implement SANetwork")
    }