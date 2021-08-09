package com.giussepr.mercadolibretest.data.network.remoteSource

import com.giussepr.mercadolibretest.data.network.api.MercadoLibreApi
import com.giussepr.mercadolibretest.data.network.mapper.MercadoLibreItemDetailInfoRemoteMapper
import com.giussepr.mercadolibretest.data.network.mapper.MercadoLibreItemRemoteMapper
import com.giussepr.mercadolibretest.data.network.model.ApiException
import com.giussepr.mercadolibretest.data.network.model.response.itemDetailResponse.ItemDetailInfo
import com.giussepr.mercadolibretest.domain.model.PagingDataDomainModel
import com.giussepr.mercadolibretest.domain.model.itemDetailInfo.MercadoLibreItemDetailInfoDomainModel
import io.reactivex.Single
import javax.inject.Inject

class MercadoLibreRemoteSourceImpl @Inject constructor(
    private val mercadoLibreApi: MercadoLibreApi,
    private val mercadoLibreItemRemoteMapper: MercadoLibreItemRemoteMapper,
    private val mercadoLibreItemDetailInfoRemoteMapper: MercadoLibreItemDetailInfoRemoteMapper
) : MercadoLibreRemoteSource {

    override fun searchItem(query: String, offset: Int, limit: Int): Single<PagingDataDomainModel> {
        return mercadoLibreApi.search(query, offset, limit)
            .map { mercadoLibreItemRemoteMapper.fromPagingRemote(it) }
    }

    override fun getItemDetailInfo(id: String): Single<MercadoLibreItemDetailInfoDomainModel> {
        return mercadoLibreApi.getItemDetailInfo(id)
            .flatMap {
                if (it.isNullOrEmpty()) {
                    Single.error<ItemDetailInfo>(ApiException(200, "Item detail info empty"))
                } else {
                    val itemDetailResponse = it.first()
                    if (itemDetailResponse.code != 200) {
                        Single.error<ItemDetailInfo>(ApiException(itemDetailResponse.code, "Error getting item detail info"))
                    } else {
                        Single.just<ItemDetailInfo>(itemDetailResponse.body)
                    }
                }
            }
            .map { mercadoLibreItemDetailInfoRemoteMapper.fromItemDetailInfoResponse(it) }
    }
}
