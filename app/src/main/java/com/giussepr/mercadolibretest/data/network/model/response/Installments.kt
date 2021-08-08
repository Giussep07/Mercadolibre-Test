package com.giussepr.mercadolibretest.data.network.model.response

data class Installments(
    val amount: Double,
    val currency_id: String,
    val quantity: Int,
    val rate: Int
)
