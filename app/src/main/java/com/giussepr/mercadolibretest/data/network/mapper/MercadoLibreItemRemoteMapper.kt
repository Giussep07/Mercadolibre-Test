package com.giussepr.mercadolibretest.data.network.mapper

import com.giussepr.mercadolibretest.data.network.model.response.searchResponse.SearchResponse
import com.giussepr.mercadolibretest.domain.model.InstallmentsDomainModel
import com.giussepr.mercadolibretest.domain.model.MercadoLibreItemDomainModel
import com.giussepr.mercadolibretest.domain.model.MercadoLibreItemDomainModel.Companion.BEST_SELLER_CANDIDATE
import com.giussepr.mercadolibretest.domain.model.MercadoLibreItemDomainModel.Companion.PROMOTION_TYPE
import com.giussepr.mercadolibretest.domain.model.PagingDataDomainModel

class MercadoLibreItemRemoteMapper {

    fun fromPagingRemote(searchResponse: SearchResponse): PagingDataDomainModel {
        val data = fromRemote(searchResponse)

        val isLastPage = searchResponse.results.size + searchResponse.paging.offset >= searchResponse.paging.total

        return PagingDataDomainModel(data, searchResponse.paging.offset, searchResponse.paging.limit, isLastPage)
    }

    fun fromRemote(searchResponse: SearchResponse): List<MercadoLibreItemDomainModel> {
        return searchResponse.results.map {

            val discountPercentage = (it.prices?.prices?.find { price -> price.type == PROMOTION_TYPE })?.metadata?.campaign_discount_percentage

            val installment = if (it.installments != null) {
                InstallmentsDomainModel(it.installments.quantity, it.installments.amount)
            } else {
                null
            }

            MercadoLibreItemDomainModel(
                it.id,
                it.title,
                it.thumbnail,
                it.price,
                it.prices?.presentation?.display_currency ?: "COP",
                installment,
                it.shipping.free_shipping,
                discountPercentage,
                it.tags.contains(BEST_SELLER_CANDIDATE),
                it.condition,
                it.sold_quantity,
                it.available_quantity
            )
        }
    }

}
