package com.giussepr.mercadolibretest.presentation.model

import androidx.recyclerview.widget.DiffUtil

interface MercadoLibreItemUiItem {

    object DiffCallback : DiffUtil.ItemCallback<MercadoLibreItemUiItem>() {
        override fun areItemsTheSame(
            oldItem: MercadoLibreItemUiItem,
            newItem: MercadoLibreItemUiItem
        ): Boolean {
            return when {
                oldItem is MercadoLibreLoadingItemUi && newItem is MercadoLibreLoadingItemUi -> true
                oldItem is MercadoLibreItemUi && newItem is MercadoLibreItemUi -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(
            oldItem: MercadoLibreItemUiItem,
            newItem: MercadoLibreItemUiItem
        ): Boolean {
            return when {
                oldItem is MercadoLibreLoadingItemUi && newItem is MercadoLibreLoadingItemUi -> true
                oldItem is MercadoLibreItemUi && newItem is MercadoLibreItemUi -> true
                else -> false
            }
        }
    }
}
