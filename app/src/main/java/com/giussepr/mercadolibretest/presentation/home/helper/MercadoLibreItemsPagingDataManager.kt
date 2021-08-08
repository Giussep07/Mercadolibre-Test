package com.giussepr.mercadolibretest.presentation.home.helper

import com.giussepr.mercadolibretest.domain.model.PagingDataDomainModel
import com.giussepr.mercadolibretest.domain.repository.MercadoLibreRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MercadoLibreItemsPagingManager @Inject constructor(private val repository: MercadoLibreRepository) {

    private var pagingSubject: PublishSubject<PagingDataDomainModel> = PublishSubject.create()
    private var pagingDisposable: Disposable? = null

    private var offset: Int = 0
    private var isInProgress: Boolean = false

    private fun loadPage(query: String, offset: Int): Single<PagingDataDomainModel> {
        return repository.searchItem(query, offset, PAGE_SIZE)
    }

    fun loadNextPage(query: String) {
        if (isInProgress) {
            return
        } else {
            isInProgress = true
        }

        pagingDisposable?.dispose()
        pagingDisposable = loadPage(query, offset)
            .subscribeOn(Schedulers.io())
            .subscribeBy(onSuccess = {
                offset = it.offset + PAGE_SIZE
                pagingSubject.onNext(it)
                isInProgress = false
            }, onError = {
                isInProgress = false
            })
    }

    fun getPagingUpdates(): Observable<PagingDataDomainModel> {
        return pagingSubject
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}
