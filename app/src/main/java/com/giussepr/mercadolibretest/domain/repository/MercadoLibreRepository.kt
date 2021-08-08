package com.giussepr.mercadolibretest.domain.repository

import com.giussepr.mercadolibretest.domain.model.MercadoLibreItemDomainModel
import io.reactivex.Single

interface MercadoLibreRepository {
    fun searchItem(query: String): Single<List<MercadoLibreItemDomainModel>>
}
