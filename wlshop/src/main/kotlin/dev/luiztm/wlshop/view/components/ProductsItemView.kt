package dev.luiztm.wlshop.view.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import coil.transform.RoundedCornersTransformation
import dev.luiztm.wlshop.R
import kotlin.random.Random.Default.nextInt


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
class ProductsItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val _title: TextView by lazy { findViewById(R.id.wlshop_product_name) }
    private val _price: TextView by lazy { findViewById(R.id.wlshop_product_price) }
    private val _image: AppCompatImageView by lazy { findViewById(R.id.wlshop_product_image) }

    init {
        inflate(context, R.layout.wlshop_products_item_view, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    fun setupView(name: String, price: String, image: String? = null, click: () -> Unit) {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(generateRandomPastelColor())
        gradientDrawable.cornerRadii = floatArrayOf(200f, 200f, 0f, 0f, 0f, 0f, 0f, 0f)
        background = gradientDrawable

        _title.text = name
        _price.text = context.getString(R.string.wlshop_price, price)
        _image.load(image) {
            crossfade(true)
            transformations(RoundedCornersTransformation(70f, 70f, 70f, 70f))
        }
        setOnClickListener { click() }
    }

    private fun generateRandomPastelColor(): Int {
        // This is the base color which will be mixed with the generated one
        val baseColor = Color.WHITE
        val baseRed = Color.red(baseColor)
        val baseGreen = Color.green(baseColor)
        val baseBlue = Color.blue(baseColor)
        val red: Int = (baseRed + nextInt(256)) / 2
        val green: Int = (baseGreen + nextInt(256)) / 2
        val blue: Int = (baseBlue + nextInt(256)) / 2
        return Color.rgb(red, green, blue)
    }
}