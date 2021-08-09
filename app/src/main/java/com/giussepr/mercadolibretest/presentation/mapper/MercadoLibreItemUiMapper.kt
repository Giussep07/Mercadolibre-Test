package com.giussepr.mercadolibretest.presentation.mapper

import com.giussepr.mercadolibretest.domain.model.MercadoLibreItemDomainModel
import com.giussepr.mercadolibretest.presentation.model.InstallmentsUiModel
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi

class MercadoLibreItemUiMapper {

    fun fromDomainList(mercadoLibreItems: List<MercadoLibreItemDomainModel>): List<MercadoLibreItemUi> {
        return mercadoLibreItems.map { fromDomain(it) }
    }

    fun fromDomain(mercadoLibreItem: MercadoLibreItemDomainModel): MercadoLibreItemUi {
        val installment = if (mercadoLibreItem.installments != null) {
            InstallmentsUiModel(mercadoLibreItem.installments.quantity, mercadoLibreItem.installments.amount)
        } else {
            null
        }

        return MercadoLibreItemUi(
            mercadoLibreItem.id,
            mercadoLibreItem.title,
            mercadoLibreItem.thumbnail,
            mercadoLibreItem.price,
            installment,
            mercadoLibreItem.isFreeShipping,
            if (mercadoLibreItem.discountPercentage != null && mercadoLibreItem.discountPercentage > 0) mercadoLibreItem.discountPercentage else null,
            mercadoLibreItem.isBestSeller,
            mercadoLibreItem.condition,
            mercadoLibreItem.soldQuantity,
            mercadoLibreItem.availableQuantity
        )
    }
}
