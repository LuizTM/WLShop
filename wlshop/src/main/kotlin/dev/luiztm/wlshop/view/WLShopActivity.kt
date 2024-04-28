package dev.luiztm.wlshop.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.data.repositories.WLShopRepositoryImpl
import dev.luiztm.wlshop.view.model.WLShopViewModel
import dev.luiztm.wlshop.view.model.wlShopViewModelFactory


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
class WLShopActivity : AppCompatActivity(R.layout.wlshop_activity) {

    private val viewmodel: WLShopViewModel by viewModels {
        wlShopViewModelFactory(repository = WLShopRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}