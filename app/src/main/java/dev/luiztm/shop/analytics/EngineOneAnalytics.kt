package dev.luiztm.shop.analytics

import dev.luiztm.sa_analytics.SAAnalytics


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
class EngineOneAnalytics : SAAnalytics {
    override fun trackAction(type: String, msg: String) {
        println("From ${EngineOneAnalytics::class.simpleName} $type $msg")
    }

    override fun trackScreen(screenName: String) {
        println("From ${EngineOneAnalytics::class.simpleName} $screenName")
    }
}