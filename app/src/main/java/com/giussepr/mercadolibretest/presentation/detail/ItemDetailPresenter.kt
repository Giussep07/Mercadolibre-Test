package com.giussepr.mercadolibretest.presentation.detail

import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi

interface ItemDetailPresenter {
    fun onViewCreated(mercadoLibreItem: MercadoLibreItemUi)
    fun onPageSelected(position: Int)
}
