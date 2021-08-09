package com.giussepr.mercadolibretest.data.network.model.response.searchResponse

data class Result(
    val condition: String,
    val id: String,
    val installments: Installments,
    val price: Long,
    val prices: Prices,
    val shipping: Shipping,
    val sold_quantity: Int,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    val available_quantity: Int
)
