package com.giussepr.mercadolibretest.data.network.remoteSource

import com.giussepr.mercadolibretest.data.network.api.MercadoLibreApi
import com.giussepr.mercadolibretest.data.network.mapper.MercadoLibreItemRemoteMapper
import com.giussepr.mercadolibretest.domain.model.MercadoLibreItemDomainModel
import io.reactivex.Single
import javax.inject.Inject

class MercadoLibreRemoteSourceImpl @Inject constructor(
    private val mercadoLibreApi: MercadoLibreApi,
    private val mercadoLibreItemRemoteMapper: MercadoLibreItemRemoteMapper
) : MercadoLibreRemoteSource {

    override fun searchItem(query: String): Single<List<MercadoLibreItemDomainModel>> {
        return mercadoLibreApi.search(query)
            .map { mercadoLibreItemRemoteMapper.fromRemote(it) }
    }
}
