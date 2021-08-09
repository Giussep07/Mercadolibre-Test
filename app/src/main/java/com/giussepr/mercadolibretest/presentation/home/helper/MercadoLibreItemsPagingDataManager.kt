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

    private var query: String = ""
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

        if (this.query != query) {
            offset = 0
        }
        this.query = query

        pagingDisposable?.dispose()
        pagingDisposable = loadPage(query, offset)
            .subscribeOn(Schedulers.io())
            .subscribeBy(onSuccess = {
                offset = it.offset + PAGE_SIZE
                pagingSubject.onNext(it)

                resetOffset(it)

                isInProgress = false
            }, onError = {
                isInProgress = false
                pagingSubject.onError(it)
                pagingSubject = PublishSubject.create()
            })
    }

    fun getPagingUpdates(): Observable<PagingDataDomainModel> {
        return pagingSubject
    }

    private fun resetOffset(pagingDataDomainModel: PagingDataDomainModel) {
        if (pagingDataDomainModel.isLastPage || !pagingDataDomainModel.isLastPage && pagingDataDomainModel.data.isEmpty()) {
            offset = 0
        }
    }

    companion object {
        const val PAGE_SIZE = 50
    }
}
