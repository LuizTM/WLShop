package dev.luiztm.shop.network

import dev.luiztm.sa_network.SANetwork
import java.io.IOException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request


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
class NetworkManager(private val client: OkHttpClient = OkHttpClient()) : SANetwork {
    override suspend fun <T: Any> makeRequest(parameters: String): Result<T> = runCatching {
        return Json.decodeFromString(run(parameters))
    }

    @Throws(IOException::class)
    fun run(path: String): String {
        val request: Request = Request.Builder()
            .url("https://fakestoreapi.com/$path")
            .build()
        client.newCall(request).execute().use { response ->
            return response.body!!.string()
        }
    }
}