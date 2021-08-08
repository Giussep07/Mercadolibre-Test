package com.giussepr.mercadolibretest.data.network.api

import com.giussepr.mercadolibretest.data.network.model.response.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadoLibreApi {

    @GET("search")
    fun search(@Query("q") query: String, @Query("offset") offset: Int, @Query("limit") limit: Int): Single<SearchResponse>

}
