package com.giussepr.mercadolibretest.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giussepr.mercadolibretest.R
import com.giussepr.mercadolibretest.databinding.ItemMercadoLibreItemBinding
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi
import com.giussepr.mercadolibretest.presentation.util.GlideImageLoader
import com.giussepr.mercadolibretest.presentation.util.ResourcesManager
import java.text.NumberFormat

class MercadoLibreItemAdapter(
    private val context: Context,
    private val resourcesManager: ResourcesManager,
    private val glideImageLoader: GlideImageLoader
) :
    ListAdapter<MercadoLibreItemUi, MercadoLibreItemAdapter.MercadoLibreItemVH>(MercadoLibreItemUi.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MercadoLibreItemVH {
        val binding =
            ItemMercadoLibreItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MercadoLibreItemVH(binding)
    }

    override fun onBindViewHolder(holder: MercadoLibreItemVH, position: Int) {
        holder.bind(getItem(position))
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

            val installmentQuantityFormat = NumberFormat.getInstance()
            installmentQuantityFormat.maximumFractionDigits = 0

            binding.tvInstallments.text = resourcesManager.getString(
                R.string.mercado_libre_item_installments,
                item.installments.quantity.toString(),
                installmentQuantityFormat.format(item.installments.amount)
            )

            binding.tvDiscountPercentage.isVisible = item.discountPercentage != null
            item.discountPercentage?.let {
                binding.tvDiscountPercentage.text = resourcesManager.getString(
                    R.string.discount_percentage,
                    it.toString()
                )
            }

            binding.tvFreeShipping.isVisible = item.isFreeShipping
            binding.tvBestSeller.isVisible = item.isBestSeller
        }
    }
}
