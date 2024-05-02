package dev.luiztm.wlshop.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.view.ContextThemeWrapper
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.luiztm.wlshop.R
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Copyright (C) 2024 LuizTM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@RunWith(AndroidJUnit4::class)
class WLShopHostActivityTest {

    @Test
    @Ignore
    fun initialActivityTest() {
        // GIVEN
        val scenario = launchActivity<WLShopHostActivity>(
            Intent(
                ContextThemeWrapper(
                    ApplicationProvider.getApplicationContext(),
                    R.style.WLShopThemeMediumContrast
                ), WLShopHostActivity::class.java
            )
        )
        // WHEN
        scenario.moveToState(Lifecycle.State.CREATED)

        // THEN
        scenario.onActivity { activity ->
            Espresso.onView(ViewMatchers.withId(R.id.wls_rv_home))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}