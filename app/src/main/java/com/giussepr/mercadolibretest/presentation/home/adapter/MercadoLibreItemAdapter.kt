package com.giussepr.mercadolibretest.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giussepr.mercadolibretest.R
import com.giussepr.mercadolibretest.databinding.ItemLoadingBinding
import com.giussepr.mercadolibretest.databinding.ItemMercadoLibreItemBinding
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUiItem
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreLoadingItemUi
import com.giussepr.mercadolibretest.presentation.util.GlideImageLoader
import com.giussepr.mercadolibretest.presentation.util.ResourcesManager
import java.lang.IllegalStateException
import java.text.NumberFormat

class MercadoLibreItemAdapter(
    private val context: Context,
    private val resourcesManager: ResourcesManager,
    private val glideImageLoader: GlideImageLoader
) : ListAdapter<MercadoLibreItemUiItem, RecyclerView.ViewHolder>(
    MercadoLibreItemUiItem.DiffCallback
) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MercadoLibreLoadingItemUi -> LOADING_VIEW_TYPE
            is MercadoLibreItemUi -> MERCADO_LIBRE_ITEM_VIEW_TYPE
            else -> throw IllegalStateException("Unknown item at position $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return when (viewType) {
            LOADING_VIEW_TYPE -> {
                val binding = ItemLoadingBinding.inflate(inflater, parent, false)
                MercadoLibreLoadingItemVH(binding)
            }
            MERCADO_LIBRE_ITEM_VIEW_TYPE -> {
                val binding = ItemMercadoLibreItemBinding.inflate(inflater, parent, false)
                MercadoLibreItemVH(binding)
            }
            else -> throw IllegalStateException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is MercadoLibreLoadingItemUi -> holder as MercadoLibreLoadingItemVH
            is MercadoLibreItemUi -> (holder as MercadoLibreItemVH).bind(item)
        }
    }

    inner class MercadoLibreItemVH(private val binding: ItemMercadoLibreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MercadoLibreItemUi) {
            glideImageLoader.load(item.thumbnail, binding.ivItemImage)
            binding.tvTitle.text = item.title
            binding.tvPrice.text = resourcesManager.getString(
                R.string.mercado_libre_item_price,
                NumberFormat.getInstance().format(item.price)
            )

            val numberFormat = NumberFormat.getInstance()
            numberFormat.maximumFractionDigits = 0

            binding.tvInstallments.text = resourcesManager.getString(
                R.string.mercado_libre_item_installments,
                item.installments.quantity.toString(),
                numberFormat.format(item.installments.amount)
            )

            binding.tvDiscountPercentage.isVisible = item.discountPercentage != null
            item.discountPercentage?.let {
                binding.tvDiscountPercentage.text = resourcesManager.getString(
                    R.string.discount_percentage,
                    numberFormat.format(it)
                )
            }

            binding.tvFreeShipping.isVisible = item.isFreeShipping
            binding.tvBestSeller.isVisible = item.isBestSeller
        }
    }

    inner class MercadoLibreLoadingItemVH(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        const val LOADING_VIEW_TYPE = 1
        const val MERCADO_LIBRE_ITEM_VIEW_TYPE = 2
    }
}
