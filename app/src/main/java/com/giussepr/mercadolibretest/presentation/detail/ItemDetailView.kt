package com.giussepr.mercadolibretest.presentation.detail

import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi
import com.giussepr.mercadolibretest.presentation.model.itemDetailInfo.ItemPictureUiModel

interface ItemDetailView {
    fun setupItemDetailInfo(mercadoLibreItem: MercadoLibreItemUi, conditionSold: String)
    fun setupViewPager(pictures: List<ItemPictureUiModel>)
    fun setViewPagerIndicator(indicator: String)
}
