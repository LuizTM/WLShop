package dev.luiztm.wlshop.data.datasources

import dev.luiztm.wlshop.data.db.CartDAO
import dev.luiztm.wlshop.data.db.CartEntity
import java.time.Clock

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
class WLShopLocalDataSourceImpl(
    override val cache: HashMap<String, Pair<Long, Any>> = hashMapOf(),
    override val expires: Long = 5_000,
    private val dao: CartDAO,
) : WLShopLocalDataSource {

    override fun get(key: String): Pair<Long, Any>? = cache[key]

    override fun <T : Any> set(key: String, value: T) {
        cache[key] = Clock.systemDefaultZone().millis() to value
    }

    override fun remove(key: String) {
        cache.remove(key)
    }

    override fun clear() {
        cache.clear()
    }

    override suspend fun addProduct(productId: Int) {
        dao.insertOrUpdate(CartEntity(productId = productId))
    }

    override suspend fun getAllCartProducts(): Result<List<CartEntity>> = runCatching {
        dao.getAllProduct()
    }
}
