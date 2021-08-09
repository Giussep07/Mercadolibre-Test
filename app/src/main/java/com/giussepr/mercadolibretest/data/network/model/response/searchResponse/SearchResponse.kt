package com.giussepr.mercadolibretest.data.network.model.response.searchResponse

data class SearchResponse(
    val paging: Paging,
    val results: List<Result>
)
