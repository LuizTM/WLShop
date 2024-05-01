package dev.luiztm.wlshop.di

import android.content.Context
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.luiztm.sa_network.SANetwork
import dev.luiztm.sa_network.injectSANetwork
import dev.luiztm.sa_network.provideSANetwork
import dev.luiztm.wlshop.data.datasources.WLShopLocalDataSource
import dev.luiztm.wlshop.data.datasources.WLShopLocalDataSourceImpl
import dev.luiztm.wlshop.data.datasources.WLShopRemoteDataSource
import dev.luiztm.wlshop.data.datasources.WLShopRemoteDataSourceImpl
import dev.luiztm.wlshop.data.repositories.WLShopRepository
import dev.luiztm.wlshop.data.repositories.WLShopRepositoryImpl
import dev.luiztm.wlshop.view.model.WLShopViewModel


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

fun AppCompatActivity.injectViewModel(): Lazy<WLShopViewModel> {
    return provideViewModel()
}
fun Fragment.injectViewModel(): Lazy<WLShopViewModel> {
    return provideViewModel()
}

private fun AppCompatActivity.provideViewModel(): Lazy<WLShopViewModel> {
    return viewModels {
        val localDataSource = provideLocalDataSource()
        val remoteDataSource = provideRemoteDataSource(provideSANetwork())
        val repository = provideRepository(remoteDataSource, localDataSource)
        wlShopViewModelFactory(repository)
    }
}

private fun Fragment.provideViewModel(): Lazy<WLShopViewModel> {
    return activityViewModels {
        val localDataSource = provideLocalDataSource()
        val remoteDataSource = provideRemoteDataSource(requireActivity().provideSANetwork())
        val repository = provideRepository(remoteDataSource, localDataSource)
        wlShopViewModelFactory(repository)
    }
}

private fun provideRepository(
    remoteDataSource: WLShopRemoteDataSource,
    localDataSource: WLShopLocalDataSource
): WLShopRepository {
    return WLShopRepositoryImpl(remoteDataSource, localDataSource)
}

private fun provideRemoteDataSource(saNetwork: SANetwork): WLShopRemoteDataSource =
    WLShopRemoteDataSourceImpl(saNetwork)

private fun provideLocalDataSource(): WLShopLocalDataSource =
    WLShopLocalDataSourceImpl()

private fun wlShopViewModelFactory(repository: WLShopRepository) = viewModelFactory {
    initializer { WLShopViewModel(repository) }
}