package com.giussepr.mercadolibretest.data.network.remoteSource

import com.giussepr.mercadolibretest.data.network.api.MercadoLibreApi
import com.giussepr.mercadolibretest.data.network.mapper.MercadoLibreItemRemoteMapper
import com.giussepr.mercadolibretest.domain.model.PagingDataDomainModel
import io.reactivex.Single
import javax.inject.Inject

class MercadoLibreRemoteSourceImpl @Inject constructor(
    private val mercadoLibreApi: MercadoLibreApi,
    private val mercadoLibreItemRemoteMapper: MercadoLibreItemRemoteMapper
) : MercadoLibreRemoteSource {

    override fun searchItem(query: String, offset: Int, limit: Int): Single<PagingDataDomainModel> {
        return mercadoLibreApi.search(query, offset, limit)
            .map { mercadoLibreItemRemoteMapper.fromPagingRemote(it) }
    }
}
