package com.giussepr.mercadolibretest.presentation.home

import android.os.Bundle
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUiItem

interface HomeView {
    fun loadItems(mercadoLibreItems: MutableList<MercadoLibreItemUiItem>)
    fun showLoading()
    fun hideLoading()
    fun clearItems()
    fun navigateToItemDetail(item: MercadoLibreItemUi)
    fun showEmptyState()
    fun hideEmptyState()
    fun showNoResults()
    fun hideNoResults()
    fun saveState(
        outState: Bundle,
        query: String,
        mercadoLibreItems: List<MercadoLibreItemUi>,
        canLoadNextPage: Boolean
    )
    fun showErrorDialog(errorMessage: String)
}
