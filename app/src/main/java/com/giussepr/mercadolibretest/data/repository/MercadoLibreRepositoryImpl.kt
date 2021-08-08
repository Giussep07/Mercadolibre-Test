package com.giussepr.mercadolibretest.data.repository

import com.giussepr.mercadolibretest.data.network.remoteSource.MercadoLibreRemoteSource
import com.giussepr.mercadolibretest.domain.model.MercadoLibreItemDomainModel
import com.giussepr.mercadolibretest.domain.repository.MercadoLibreRepository
import io.reactivex.Single
import javax.inject.Inject

class MercadoLibreRepositoryImpl @Inject constructor(
    private val mercadoLibreRemoteSource: MercadoLibreRemoteSource
) : MercadoLibreRepository {

    override fun searchItem(query: String): Single<List<MercadoLibreItemDomainModel>> {
        return mercadoLibreRemoteSource.searchItem(query)
    }
}
