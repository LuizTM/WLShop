package dev.luiztm.wlshop.data.datasources

import dev.luiztm.wlshop.data.db.CartEntity


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
interface WLShopLocalDataSource {
    val cache: HashMap<String, Pair<Long, Any>>
    val expires: Long
    fun get(key: String): Pair<Long, Any>?
    fun <T: Any> set(key: String, value: T)
    fun remove(key: String)
    fun clear()
    suspend fun addProduct(productId: Int)

    suspend fun increaseCartItem(productId: Int)
    suspend fun decreaseCartItem(productId: Int)
    suspend fun getAllCartProducts(): Result<List<CartEntity>>
}