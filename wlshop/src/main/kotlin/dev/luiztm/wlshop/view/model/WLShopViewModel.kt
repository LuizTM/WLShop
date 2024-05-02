package dev.luiztm.wlshop.view.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.luiztm.wlshop.data.model.cart.CartResponseItem
import dev.luiztm.wlshop.data.model.cart.Product
import dev.luiztm.wlshop.data.model.product.ProductsResponseItem
import dev.luiztm.wlshop.data.repositories.WLShopRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


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

sealed interface UIStateHome {
    data class Success(val data: List<ProductsResponseItem>) : UIStateHome
    data class SuccessCart(val data: List<CartResponseItem>) : UIStateHome
    object Loading : UIStateHome
    object Error : UIStateHome
}

class WLShopViewModel(
    private val repository: WLShopRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _productItems: MutableSharedFlow<UIStateHome> = MutableStateFlow(UIStateHome.Loading)
    val productItems = _productItems.shareIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000)
    )

    fun getAllProducts() {
        viewModelScope.launch(dispatcher) {
            _productItems.emit(UIStateHome.Loading)
            repository.getAllProducts()
                .onSuccess {
                    _productItems.emit(UIStateHome.Success(it))
                }
                .onFailure {
                    Log.e("WLShopViewModel", it.message, it)
                    _productItems.emit(UIStateHome.Error)
                }
        }
    }

    fun addProductToCart(productId: Int){
        viewModelScope.launch(dispatcher) {
            repository.addProductToCart(productId)
        }
    }

    fun increaseCartItem(productId: Int){
        viewModelScope.launch(dispatcher) {
            repository.increaseCartItem(productId)
        }
    }

    fun decreaseCartItem(productId: Int){
        viewModelScope.launch(dispatcher) {
            repository.decreaseCartItem(productId)
        }
    }

    fun getAllCartProducts() {
        viewModelScope.launch(dispatcher) {
            _productItems.emit(UIStateHome.Loading)
            repository.getAllCartProducts()
                .onSuccess {
                    _productItems.emit(UIStateHome.SuccessCart(it))
                }
                .onFailure {
                    Log.e("WLShopViewModel", it.message, it)
                    _productItems.emit(UIStateHome.Error)
                }
        }
    }
}