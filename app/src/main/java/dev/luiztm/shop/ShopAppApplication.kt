package dev.luiztm.shop

import android.app.Application
import dev.luiztm.sa_analytics.SAAnalytics
import dev.luiztm.sa_network.SANetwork
import dev.luiztm.shop.analytics.AnalyticsManager
import dev.luiztm.shop.analytics.EngineOneAnalytics
import dev.luiztm.shop.analytics.EngineTwoAnalytics
import dev.luiztm.shop.network.NetworkManager

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

class ShopAppApplication : Application(),
    SANetwork by NetworkManager(),
    SAAnalytics by AnalyticsManager(
        EngineOneAnalytics(),
        EngineTwoAnalytics()
    )