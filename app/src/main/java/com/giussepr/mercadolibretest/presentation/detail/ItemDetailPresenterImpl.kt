package com.giussepr.mercadolibretest.presentation.detail

import com.giussepr.mercadolibretest.R
import com.giussepr.mercadolibretest.domain.repository.MercadoLibreRepository
import com.giussepr.mercadolibretest.presentation.mapper.MercadoLibreItemDetailInfoUiMapper
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi
import com.giussepr.mercadolibretest.presentation.model.itemDetailInfo.ItemDetailInfoUiModel
import com.giussepr.mercadolibretest.presentation.util.ResourcesManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ItemDetailPresenterImpl @Inject constructor(
    private val view: ItemDetailView,
    private val resourcesManager: ResourcesManager,
    private val repository: MercadoLibreRepository,
    private val mercadoLibreItemDetailInfoUiMapper: MercadoLibreItemDetailInfoUiMapper
) : ItemDetailPresenter {

    private lateinit var mercadoLibreItem: MercadoLibreItemUi
    private lateinit var itemDetailInfo: ItemDetailInfoUiModel
    private var itemDetailDisposable: Disposable? = null

    override fun onViewCreated(mercadoLibreItem: MercadoLibreItemUi) {
        this.mercadoLibreItem = mercadoLibreItem

        fetchItemDetailInfo()

        setupItemDetailInfo()
    }

    override fun onPageSelected(position: Int) {
        setupViewpagerIndicator(position)
    }

    private fun setupItemDetailInfo() {
        val condition = if (mercadoLibreItem.condition == MercadoLibreItemUi.NEW) {
            resourcesManager.getString(R.string.condition_new)
        } else {
            mercadoLibreItem.condition
        }

        val conditionSold = resourcesManager.getString(
            R.string.condition_sold_quantity,
            condition,
            mercadoLibreItem.soldQuantity
        )

        view.setupItemDetailInfo(mercadoLibreItem, conditionSold)
    }

    private fun fetchItemDetailInfo() {
        itemDetailDisposable?.dispose()
        itemDetailDisposable = repository.getItemDetailInfo(mercadoLibreItem.id)
            .map { mercadoLibreItemDetailInfoUiMapper.fromDomainModel(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = {
                this.itemDetailInfo = it
                view.setupViewPager(it.pictures)
                setupViewpagerIndicator()
            }, onError = {
                println("Pepe: Error fetchItemDetailInfo: $it")
            })
    }

    private fun setupViewpagerIndicator(position: Int = 0) {
        val indicator = resourcesManager.getString(
            R.string.viewpager_pictures_indicator,
            position + 1,
            itemDetailInfo.pictures.size
        )

        view.setViewPagerIndicator(indicator)
    }
}
