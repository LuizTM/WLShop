package dev.luiztm.wlshop.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.luiztm.wlshop.data.repositories.WLShopRepository


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

fun wlShopViewModelFactory(repository: WLShopRepository) = viewModelFactory {
    initializer { WLShopViewModel(repository) }
}

//class WLShopViewModelFactory(private val repository: WLShopRepository) : ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return WLShopViewModel(repository) as T
//    }
//}