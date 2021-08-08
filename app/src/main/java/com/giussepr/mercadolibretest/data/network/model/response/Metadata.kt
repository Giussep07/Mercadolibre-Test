package com.giussepr.mercadolibretest.data.network.model.response

data class Metadata(
    val campaign_discount_percentage: Int,
    val campaign_end_date: String,
    val campaign_id: String,
    val discount_meli_amount: Int,
    val order_item_price: Int,
    val promotion_id: String,
    val promotion_type: String
)
