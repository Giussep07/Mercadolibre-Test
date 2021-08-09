package com.giussepr.mercadolibretest.di

import androidx.fragment.app.Fragment
import com.giussepr.mercadolibretest.di.scope.FragmentScope
import com.giussepr.mercadolibretest.domain.repository.MercadoLibreRepository
import com.giussepr.mercadolibretest.presentation.home.HomeFragment
import com.giussepr.mercadolibretest.presentation.home.HomePresenter
import com.giussepr.mercadolibretest.presentation.home.HomePresenterImpl
import com.giussepr.mercadolibretest.presentation.home.HomeView
import com.giussepr.mercadolibretest.presentation.home.helper.MercadoLibreItemsPagingManager
import com.giussepr.mercadolibretest.presentation.mapper.MercadoLibreItemUiMapper
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [HomeBindModule::class])
class HomeModule {

    @Provides
    fun provideHomePresenter(
        view: HomeView,
        mercadoLibreItemsPagingManager: MercadoLibreItemsPagingManager,
        mercadoLibreItemUiMapper: MercadoLibreItemUiMapper
    ): HomePresenter {
        return HomePresenterImpl(view, mercadoLibreItemsPagingManager, mercadoLibreItemUiMapper)
    }

    @Provides
    fun provideMercadoLibreItemUiMapper(): MercadoLibreItemUiMapper = MercadoLibreItemUiMapper()

    @Provides
    fun provideMercadoLibreItemsPagingManager(repository: MercadoLibreRepository): MercadoLibreItemsPagingManager {
        return MercadoLibreItemsPagingManager(repository)
    }

}

@Module
interface HomeBindModule {
    @Binds
    fun bindView(fragment: HomeFragment): HomeView

    @Binds fun bindFragment(fragment: HomeFragment): Fragment
}
