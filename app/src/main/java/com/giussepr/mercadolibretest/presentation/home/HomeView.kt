package com.giussepr.mercadolibretest.presentation.home

import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUiItem

interface HomeView {
    fun loadItems(mercadoLibreItems: MutableList<MercadoLibreItemUiItem>)
}
