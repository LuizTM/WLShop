package dev.luiztm.wlshop.view.components

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.cardview.widget.CardView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.R.*
import com.google.android.material.color.MaterialColors
import dev.luiztm.wlshop.R
import dev.luiztm.wlshop.data.model.product.Rating
import dev.luiztm.wlshop.extensions.getContrastColor


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
) : CardView(context, attrs, defStyleAttr) {

    private val _title: TextView by lazy { findViewById(R.id.wlshop_product_name) }
    private val _price: TextView by lazy { findViewById(R.id.wlshop_product_price) }
    private val _image: AppCompatImageView by lazy { findViewById(R.id.wlshop_product_image) }
    private val _ratingStart: AppCompatRatingBar by lazy { findViewById(R.id.wlshop_rating_start) }
    private val _ratingValue: TextView by lazy { findViewById(R.id.wlshop_rating_value) }

    init {
        inflate(context, R.layout.wlshop_products_item_view, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    fun setupView(
        name: String,
        price: String,
        image: String? = null,
        rating: Rating,
        click: () -> Unit,
    ) {
        val (bgColor, textColor) = getContrastColor(MaterialColors.getColor(this@ProductsItemView, attr.colorPrimaryInverse))
        backgroundConfig(bgColor)

        _ratingStart.rating = rating.rate.toFloat()

        _ratingValue.labelsConfig(textColor, rating.rate.toString())

        _title.labelsConfig(textColor, name)
        _price.apply {
            labelsConfig(textColor, context.getString(R.string.wlshop_price, price))
        }
        _image.load(image) {
            crossfade(true)
            transformations(RoundedCornersTransformation(70f, 70f, 70f, 70f))
        }
        setOnClickListener { click() }
    }

    private fun TextView.labelsConfig(@ColorInt textColor:Int, label:String){
        typeface = Typeface.DEFAULT_BOLD
        text = label
        setTextColor(textColor)
    }

    private fun backgroundConfig(bgColor: Int) {
        background = GradientDrawable().apply {
            setStroke(8, MaterialColors.getColor(this@ProductsItemView, attr.colorPrimary))
            setColor(bgColor)
            cornerRadii = floatArrayOf(200f, 200f, 0f, 0f, 0f, 0f, 0f, 0f)
        }
    }
}
