package com.giussepr.mercadolibretest.data.network.mapper

import com.giussepr.mercadolibretest.data.network.model.response.SearchResponse
import com.giussepr.mercadolibretest.domain.model.InstallmentsDomainModel
import com.giussepr.mercadolibretest.domain.model.MercadoLibreItemDomainModel
import com.giussepr.mercadolibretest.domain.model.MercadoLibreItemDomainModel.Companion.BEST_SELLER_CANDIDATE
import com.giussepr.mercadolibretest.domain.model.MercadoLibreItemDomainModel.Companion.PROMOTION_TYPE

class MercadoLibreItemRemoteMapper {

    fun fromRemote(searchResponse: SearchResponse): List<MercadoLibreItemDomainModel> {
        return searchResponse.results.map {

            val discountPercentage = (it.prices.prices.find { price -> price.type == PROMOTION_TYPE })?.metadata?.campaign_discount_percentage

            MercadoLibreItemDomainModel(
                it.id,
                it.title,
                it.thumbnail,
                it.price,
                it.prices.presentation.display_currency,
                InstallmentsDomainModel(it.installments.quantity, it.installments.amount),
                it.shipping.free_shipping,
                discountPercentage,
                it.tags.contains(BEST_SELLER_CANDIDATE)
            )
        }
    }
}
