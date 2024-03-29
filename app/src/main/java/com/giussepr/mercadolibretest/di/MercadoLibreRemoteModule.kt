package com.giussepr.mercadolibretest.di

import com.giussepr.mercadolibretest.data.network.api.MercadoLibreApi
import com.giussepr.mercadolibretest.data.network.mapper.MercadoLibreItemDetailInfoRemoteMapper
import com.giussepr.mercadolibretest.data.network.mapper.MercadoLibreItemRemoteMapper
import com.giussepr.mercadolibretest.data.network.remoteSource.MercadoLibreRemoteSource
import com.giussepr.mercadolibretest.data.network.remoteSource.MercadoLibreRemoteSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MercadoLibreRemoteModule {

    @Singleton
    @Provides
    fun provideMercadoLibreRemoteSource(
        mercadoLibreApi: MercadoLibreApi,
        mercadoLibreItemRemoteMapper: MercadoLibreItemRemoteMapper,
        mercadoLibreItemDetailInfoRemoteMapper: MercadoLibreItemDetailInfoRemoteMapper
    ): MercadoLibreRemoteSource {
        return MercadoLibreRemoteSourceImpl(mercadoLibreApi,
            mercadoLibreItemRemoteMapper,
            mercadoLibreItemDetailInfoRemoteMapper)
    }

    @Singleton
    @Provides
    fun provideMercadoLibreItemRemoteMapper(): MercadoLibreItemRemoteMapper =
        MercadoLibreItemRemoteMapper()

    @Singleton
    @Provides
    fun provideMercadoLibreItemDetailInfoRemoteMapper(): MercadoLibreItemDetailInfoRemoteMapper =
        MercadoLibreItemDetailInfoRemoteMapper()

}
