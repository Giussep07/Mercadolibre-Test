package com.giussepr.mercadolibretest.presentation.home

import com.giussepr.mercadolibretest.domain.repository.MercadoLibreRepository
import com.giussepr.mercadolibretest.presentation.mapper.MercadoLibreItemUiMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(
    private val view: HomeView,
    private val repository: MercadoLibreRepository,
    private val mercadoLibreItemUiMapper: MercadoLibreItemUiMapper
) : HomePresenter {

    private var searchItemDisposable: Disposable? = null

    override fun searchItem(query: String) {
        searchItemDisposable?.dispose()
        searchItemDisposable = repository.searchItem(query)
            .map { mercadoLibreItemUiMapper.fromDomainList(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = {
                view.loadItems(it)
            })
    }
}
