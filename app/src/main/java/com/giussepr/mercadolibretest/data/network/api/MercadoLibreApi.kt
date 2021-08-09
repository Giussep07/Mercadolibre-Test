package com.giussepr.mercadolibretest.data.network.api

import com.giussepr.mercadolibretest.data.network.model.response.itemDetailResponse.ItemDetailResponse
import com.giussepr.mercadolibretest.data.network.model.response.searchResponse.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadoLibreApi {

    @GET("sites/MCO/search")
    fun search(@Query("q") query: String, @Query("offset") offset: Int, @Query("limit") limit: Int): Single<SearchResponse>

    @GET("items")
    fun getItemDetailInfo(@Query("ids") id: String): Single<List<ItemDetailResponse>>

}
