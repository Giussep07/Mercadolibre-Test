package com.giussepr.mercadolibretest.presentation.home

import com.giussepr.mercadolibretest.presentation.home.helper.MercadoLibreItemsPagingManager
import com.giussepr.mercadolibretest.presentation.mapper.MercadoLibreItemUiMapper
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUi
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreItemUiItem
import com.giussepr.mercadolibretest.presentation.model.MercadoLibreLoadingItemUi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(
    private val view: HomeView,
    private val mercadoLibreItemsPagingManager: MercadoLibreItemsPagingManager,
    private val mercadoLibreItemUiMapper: MercadoLibreItemUiMapper
) : HomePresenter {

    private var pagingUpdatesDisposable: Disposable? = null
    private var query: String = ""
    private var mercadoLibreItems: MutableList<MercadoLibreItemUiItem> = mutableListOf()
    private var canLoadNextPage: Boolean = false

    override fun onViewCreated() {
        view.showEmptyState()
        subscribeToPagingUpdates()
    }

    override fun onDestroyView() {
        println("Destroy")
        pagingUpdatesDisposable?.dispose()
    }

    override fun searchItem(query: String) {
        view.hideEmptyState()
        view.hideNoResults()
        view.showLoading()

        if (this.query != query) {
            mercadoLibreItems.clear()
            view.clearItems()
        }

        this.query = query

        mercadoLibreItemsPagingManager.loadNextPage(this.query)
    }

    override fun loadNextPage() {
        if (canLoadNextPage) {
            showLoadingAdapterItem()
            mercadoLibreItemsPagingManager.loadNextPage(this.query)
        }
    }

    override fun onItemClicked(item: MercadoLibreItemUi) {
        view.navigateToItemDetail(item)
    }

    private fun subscribeToPagingUpdates() {
        canLoadNextPage = true

        pagingUpdatesDisposable?.dispose()
        pagingUpdatesDisposable = mercadoLibreItemsPagingManager.getPagingUpdates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                mercadoLibreItems.addAll(mercadoLibreItemUiMapper.fromDomainList(it.data))
                removeLoadingAdapterItem()

                if (it.data.isNotEmpty()) {
                    view.loadItems(mercadoLibreItems)
                } else if (it.offset == 0 && it.data.isEmpty()) {
                    view.showNoResults()
                }

                view.hideLoading()
                canLoadNextPage = !it.isLastPage
            }, onComplete = {
                canLoadNextPage = false
            }, onError = {
                canLoadNextPage = false
            })
    }

    private fun showLoadingAdapterItem() {
        val isPresent = mercadoLibreItems.any { it is MercadoLibreLoadingItemUi }
        if (!isPresent) {
            mercadoLibreItems.add(MercadoLibreLoadingItemUi())
            view.loadItems(mercadoLibreItems)
        }
    }

    private fun removeLoadingAdapterItem() {
        mercadoLibreItems.find { it is MercadoLibreLoadingItemUi }?.let {
            mercadoLibreItems.remove(it)
        }
    }
}
