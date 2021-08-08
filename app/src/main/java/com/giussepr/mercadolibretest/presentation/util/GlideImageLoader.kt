package com.giussepr.mercadolibretest.presentation.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

class GlideImageLoader @Inject constructor(private val context: Context) {

    fun load(url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }
}
