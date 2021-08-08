package com.giussepr.mercadolibretest.presentation.model

import androidx.recyclerview.widget.DiffUtil

data class MercadoLibreItemUi(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Long,
    val installments: InstallmentsUiModel,
    val isFreeShipping: Boolean,
    val discountPercentage: Int?,
    val isBestSeller: Boolean
) {
    object DiffCallback : DiffUtil.ItemCallback<MercadoLibreItemUi>() {
        override fun areItemsTheSame(
            oldItem: MercadoLibreItemUi,
            newItem: MercadoLibreItemUi
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MercadoLibreItemUi,
            newItem: MercadoLibreItemUi
        ): Boolean {
            return oldItem == newItem
        }
    }
}
