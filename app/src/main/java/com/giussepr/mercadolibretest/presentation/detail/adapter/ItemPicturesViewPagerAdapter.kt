package com.giussepr.mercadolibretest.presentation.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.giussepr.mercadolibretest.databinding.ItemPictureBinding
import com.giussepr.mercadolibretest.presentation.model.itemDetailInfo.ItemPictureUiModel
import com.giussepr.mercadolibretest.presentation.util.GlideImageLoader

class ItemPicturesViewPagerAdapter(
    private val context: Context,
    private val pictures: List<ItemPictureUiModel>,
    private val imageLoader: GlideImageLoader
) : PagerAdapter() {

    override fun getCount(): Int = pictures.size

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view == o
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)

        val binding = ItemPictureBinding.inflate(layoutInflater, container, false)

        val itemPicture = pictures[position]

        imageLoader.load(itemPicture.url, binding.ivItemPicture)

        container.addView(binding.root)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }
}
