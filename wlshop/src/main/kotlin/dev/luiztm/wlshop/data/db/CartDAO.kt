package dev.luiztm.wlshop.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction


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
@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product: CartEntity): Long

    @Query("SELECT * FROM cart_table")
    suspend fun getAllProduct():List<CartEntity>

    @Delete
    suspend fun deleteProduct(product: CartEntity)

    @Query("UPDATE cart_table SET quantity = quantity + 1 WHERE product_id = :productId")
    suspend fun increaseBasketProductQuantity(productId: Int)

    @Query("UPDATE cart_table SET quantity = quantity - 1 WHERE product_id = :productId")
    suspend fun decreaseBasketProductQuantity(productId: Int)

    @Query("SELECT quantity FROM cart_table WHERE product_id = :productId")
    @Transaction
    suspend fun getBasketProductQuantity(productId: Long): Int

    @Transaction
    suspend fun insertOrUpdate(model: CartEntity): Long {
        val id = addProduct(model)
        return if (id==-1L) {
            increaseBasketProductQuantity(model.productId)
            model.productId.toLong()
        } else {
            id
        }
    }
}