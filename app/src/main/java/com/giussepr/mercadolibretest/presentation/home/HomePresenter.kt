package com.giussepr.mercadolibretest.presentation.home

import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi

interface HomePresenter {
    fun onViewCreated()
    fun onDestroyView()
    fun searchItem(query: String)
    fun loadNextPage()
    fun onItemClicked(item: MercadoLibreItemUi)
}
