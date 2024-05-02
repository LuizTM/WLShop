package dev.luiztm.wlshop.data.datasources

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.luiztm.sa_network.SANetwork
import dev.luiztm.wlshop.data.model.product.ProductsResponseItem
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


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

class WLShopRemoteDataSourceImpl(private val saNetwork: SANetwork) : WLShopRemoteDataSource {
    override suspend fun products(): Result<List<ProductsResponseItem>> =
        parseData(
            saNetwork.makeRequest("products"),
            Types.newParameterizedType(
                List::class.java,
                ProductsResponseItem::class.java
            )
        )

    override suspend fun productsByID(id: Int): Result<ProductsResponseItem> =
        parseData(
            saNetwork.makeRequest("products/$id"),
            Types.newParameterizedType(
                ProductsResponseItem::class.java,
                ProductsResponseItem::class.java
            )
        )

    private inline fun <reified T> parseData(data: Result<String>, parameterizedType: ParameterizedType) =
        runCatching {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val listOfCardsType: Type = parameterizedType
            val jsonAdapter = moshi.adapter<T>(listOfCardsType)
            jsonAdapter.fromJson(data.getOrThrow())!!
        }
}