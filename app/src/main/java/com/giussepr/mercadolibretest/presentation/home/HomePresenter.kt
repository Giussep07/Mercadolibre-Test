package com.giussepr.mercadolibretest.presentation.home

import android.os.Bundle
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi

interface HomePresenter {
    fun onViewCreated(
        query: String,
        mercadoLibreItems: List<MercadoLibreItemUi>?,
        canLoadNextPage: Boolean
    )
    fun onDestroyView()
    fun searchItem(query: String)
    fun loadNextPage()
    fun onItemClicked(item: MercadoLibreItemUi)
    fun onSaveInstanceState(outState: Bundle)
}
