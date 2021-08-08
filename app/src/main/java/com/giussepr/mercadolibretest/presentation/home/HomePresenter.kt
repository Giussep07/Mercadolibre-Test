package com.giussepr.mercadolibretest.presentation.home

interface HomePresenter {
    fun onViewCreated()
    fun searchItem(query: String)
    fun loadNextPage()
}
