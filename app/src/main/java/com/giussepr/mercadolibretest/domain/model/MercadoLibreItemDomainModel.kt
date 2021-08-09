package com.giussepr.mercadolibretest.domain.model

data class MercadoLibreItemDomainModel(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Long,
    val currency: String,
    val installments: InstallmentsDomainModel?,
    val isFreeShipping: Boolean,
    val discountPercentage: Double?,
    val isBestSeller: Boolean,
    val condition: String,
    val soldQuantity: Int,
    val availableQuantity: Int
) {
    companion object {
        const val PROMOTION_TYPE = "promotion"
        const val BEST_SELLER_CANDIDATE = "best_seller_candidate"
    }
}
