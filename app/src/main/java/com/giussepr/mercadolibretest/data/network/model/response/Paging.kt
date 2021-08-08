package com.giussepr.mercadolibretest.data.network.model.response

data class Paging(
    val limit: Int,
    val offset: Int,
    val primary_results: Int,
    val total: Int
)
