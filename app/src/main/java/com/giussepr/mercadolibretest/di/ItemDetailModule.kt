package com.giussepr.mercadolibretest.di

import androidx.fragment.app.Fragment
import com.giussepr.mercadolibretest.domain.repository.MercadoLibreRepository
import com.giussepr.mercadolibretest.presentation.detail.ItemDetailFragment
import com.giussepr.mercadolibretest.presentation.detail.ItemDetailPresenter
import com.giussepr.mercadolibretest.presentation.detail.ItemDetailPresenterImpl
import com.giussepr.mercadolibretest.presentation.detail.ItemDetailView
import com.giussepr.mercadolibretest.presentation.mapper.MercadoLibreItemDetailInfoUiMapper
import com.giussepr.mercadolibretest.presentation.util.ResourcesManager
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ItemDetailBindModule::class])
class ItemDetailModule {

    @Provides
    fun provideHomePresenter(
        view: ItemDetailView,
        resourcesManager: ResourcesManager,
        repository: MercadoLibreRepository,
        mercadoLibreItemDetailInfoUiMapper: MercadoLibreItemDetailInfoUiMapper
    ): ItemDetailPresenter {
        return ItemDetailPresenterImpl(view, resourcesManager, repository, mercadoLibreItemDetailInfoUiMapper)
    }

    @Provides
    fun provideMercadoLibreItemDetailInfoUiMapper(): MercadoLibreItemDetailInfoUiMapper =
        MercadoLibreItemDetailInfoUiMapper()

}

@Module
interface ItemDetailBindModule {
    @Binds
    fun bindView(fragment: ItemDetailFragment): ItemDetailView

    @Binds fun bindFragment(fragment: ItemDetailFragment): Fragment
}
