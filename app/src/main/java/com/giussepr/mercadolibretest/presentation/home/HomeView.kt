package com.giussepr.mercadolibretest.presentation.home

import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi

interface HomeView {
    fun loadItems(mercadoLibreItems: List<MercadoLibreItemUi>)
}
