package com.giussepr.mercadolibretest.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MercadoLibreItemUi(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Long,
    val installments: InstallmentsUiModel?,
    val isFreeShipping: Boolean,
    val discountPercentage: Double?,
    val isBestSeller: Boolean,
    val condition: String,
    val soldQuantity: Int,
    val availableQuantity: Int
): MercadoLibreItemUiItem, Parcelable {
    companion object {
        const val NEW = "new"
    }
}
