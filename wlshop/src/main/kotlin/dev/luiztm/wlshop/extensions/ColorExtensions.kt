package dev.luiztm.wlshop.extensions

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils
import com.google.android.material.R
import com.google.android.material.color.MaterialColors
import kotlin.random.Random


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

fun View.generateRandomPastelColor(): Pair<Int, Int> {
    val baseColor = Color.WHITE
    val baseRed = Color.red(baseColor)
    val baseGreen = Color.green(baseColor)
    val baseBlue = Color.blue(baseColor)
    val red: Int = (baseRed + Random.nextInt(256)) / 2
    val green: Int = (baseGreen + Random.nextInt(256)) / 2
    val blue: Int = (baseBlue + Random.nextInt(256)) / 2
    return getContrastColor(Color.rgb(red, green, blue))
}

fun View.getContrastColor(@ColorInt color: Int): Pair<Int, Int> {
    val whiteContrast = ColorUtils.calculateContrast(Color.WHITE, color)
    val blackContrast = ColorUtils.calculateContrast(Color.BLACK, color)
    val value = if (whiteContrast > blackContrast) MaterialColors.getColor(this, R.attr.colorOnSurface) else MaterialColors.getColor(this, R.attr.colorOnSurface)
    return color to value
}