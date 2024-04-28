package dev.luiztm.wlshop.data.repositories

import dev.luiztm.wlshop.data.datasources.WLShopLocalDataSource
import dev.luiztm.wlshop.data.datasources.WLShopRemoteDataSource


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
class WLShopRepositoryImpl(
    remoteDataSource: WLShopRemoteDataSource = WLShopRemoteDataSource(),
    localDataSource: WLShopLocalDataSource = WLShopLocalDataSource()
) : WLShopRepository {
    override fun getAllProducts(): Result<List<Any>> {
        TODO("Not yet implemented")
    }

    override fun getAllCartProducts(): Result<List<Any>> {
        TODO("Not yet implemented")
    }

    override fun getProductByID(id: Int): Result<Any> {
        TODO("Not yet implemented")
    }

    override fun addCartProduct(id: Int): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override fun removeCartProduct(id: Int): Result<Boolean> {
        TODO("Not yet implemented")
    }
}