package dev.luiztm.wlshop.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.view.fragments.WLShopHomeFragment
import dev.luiztm.wlshop.view.fragments.routes.NavigationRoutes


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        navController.graph = navController.createGraph(
            startDestination = NavigationRoutes.home
        ) {
            fragment<WLShopHomeFragment>(NavigationRoutes.home) {
                label = "Home"
            }

//            fragment<PlantDetailFragment>("${NavigationRoutes.plant_detail}/{${NavigationRoutes.plant_id}}") {
//                label = resources.getString(R.string.plant_detail_title)
//                argument(NavigationRoutes.plant_id) {
//                    type = NavType.StringType
//                }
//            }
        }
    }
}