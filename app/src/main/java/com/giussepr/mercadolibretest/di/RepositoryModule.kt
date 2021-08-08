package com.giussepr.mercadolibretest.di

import com.giussepr.mercadolibretest.data.repository.MercadoLibreRepositoryImpl
import com.giussepr.mercadolibretest.domain.repository.MercadoLibreRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsMercadoLibreRepository(repository: MercadoLibreRepositoryImpl): MercadoLibreRepository
}
