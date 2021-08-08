package com.giussepr.mercadolibretest.data.network.model.response

data class SearchResponse(
    val available_filters: List<AvailableFilter>,
    val available_sorts: List<AvailableSort>,
    val country_default_time_zone: String,
    val filters: List<Any>,
    val paging: Paging,
    val query: String,
    val related_results: List<Any>,
    val results: List<Result>,
    val secondary_results: List<Any>,
    val site_id: String,
    val sort: Sort
)
