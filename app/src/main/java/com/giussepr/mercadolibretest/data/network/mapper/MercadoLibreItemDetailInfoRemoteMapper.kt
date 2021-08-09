package com.giussepr.mercadolibretest.data.network.mapper

import com.giussepr.mercadolibretest.data.network.model.response.itemDetailResponse.ItemDetailInfo
import com.giussepr.mercadolibretest.data.network.model.response.itemDetailResponse.ItemPictureResponse
import com.giussepr.mercadolibretest.domain.model.itemDetailInfo.MercadoLibreItemDetailInfoDomainModel
import com.giussepr.mercadolibretest.domain.model.itemDetailInfo.ItemPictureDomainModel

class MercadoLibreItemDetailInfoRemoteMapper {

    fun fromItemDetailInfoResponse(itemDetailInfo: ItemDetailInfo): MercadoLibreItemDetailInfoDomainModel {
        return MercadoLibreItemDetailInfoDomainModel(
            itemDetailInfo.id,
            fromItemPictureResponse(itemDetailInfo.pictures)
        )
    }

    private fun fromItemPictureResponse(itemPictureResponse: List<ItemPictureResponse>): List<ItemPictureDomainModel> {
        return itemPictureResponse.map { ItemPictureDomainModel(it.id, it.url) }
    }
}
