package com.giussepr.mercadolibretest.data.network.remoteSource

import com.giussepr.mercadolibretest.domain.model.MercadoLibreItemDomainModel
import io.reactivex.Single

interface MercadoLibreRemoteSource {
    fun searchItem(query: String): Single<List<MercadoLibreItemDomainModel>>
}
