package com.giussepr.mercadolibretest.presentation.model

data class MercadoLibreItemUi(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Long,
    val installments: InstallmentsUiModel,
    val isFreeShipping: Boolean,
    val discountPercentage: Double?,
    val isBestSeller: Boolean
): MercadoLibreItemUiItem
