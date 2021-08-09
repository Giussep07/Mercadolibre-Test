package com.giussepr.mercadolibretest.data.repository

import com.giussepr.mercadolibretest.data.network.remoteSource.MercadoLibreRemoteSource
import com.giussepr.mercadolibretest.domain.model.PagingDataDomainModel
import com.giussepr.mercadolibretest.domain.model.itemDetailInfo.MercadoLibreItemDetailInfoDomainModel
import com.giussepr.mercadolibretest.domain.repository.MercadoLibreRepository
import io.reactivex.Single
import javax.inject.Inject

class MercadoLibreRepositoryImpl @Inject constructor(
    private val mercadoLibreRemoteSource: MercadoLibreRemoteSource
) : MercadoLibreRepository {

    override fun searchItem(query: String, offset: Int, limit: Int): Single<PagingDataDomainModel> {
        return mercadoLibreRemoteSource.searchItem(query, offset, limit)
    }

    override fun getItemDetailInfo(id: String): Single<MercadoLibreItemDetailInfoDomainModel> {
        return mercadoLibreRemoteSource.getItemDetailInfo(id)
    }
}
