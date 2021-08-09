package com.giussepr.mercadolibretest.domain.model

data class PagingDataDomainModel(
    val data: List<MercadoLibreItemDomainModel>,
    val offset: Int,
    val limit: Int,
    val isLastPage: Boolean
)
