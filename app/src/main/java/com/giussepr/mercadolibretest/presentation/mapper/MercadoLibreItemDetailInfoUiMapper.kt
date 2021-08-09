package com.giussepr.mercadolibretest.presentation.mapper

import com.giussepr.mercadolibretest.domain.model.itemDetailInfo.ItemPictureDomainModel
import com.giussepr.mercadolibretest.domain.model.itemDetailInfo.MercadoLibreItemDetailInfoDomainModel
import com.giussepr.mercadolibretest.presentation.model.itemDetailInfo.ItemDetailInfoUiModel
import com.giussepr.mercadolibretest.presentation.model.itemDetailInfo.ItemPictureUiModel

class MercadoLibreItemDetailInfoUiMapper {

    fun fromDomainModel(domain: MercadoLibreItemDetailInfoDomainModel): ItemDetailInfoUiModel {
        return ItemDetailInfoUiModel(
            domain.id,
            fromItemPictureDomainModel(domain.pictures)
        )
    }

    private fun fromItemPictureDomainModel(domain: List<ItemPictureDomainModel>): List<ItemPictureUiModel> {
        return domain.map { ItemPictureUiModel(it.id, it.url) }
    }
}
