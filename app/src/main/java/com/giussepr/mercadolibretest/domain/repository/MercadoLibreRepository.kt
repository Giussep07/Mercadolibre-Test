package com.giussepr.mercadolibretest.domain.repository

import com.giussepr.mercadolibretest.domain.model.PagingDataDomainModel
import io.reactivex.Single

interface MercadoLibreRepository {
    fun searchItem(query: String, offset: Int, limit: Int): Single<PagingDataDomainModel>
}
