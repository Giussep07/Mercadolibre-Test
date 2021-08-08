package com.giussepr.mercadolibretest.di

import androidx.fragment.app.Fragment
import com.giussepr.mercadolibretest.di.scope.FragmentScope
import com.giussepr.mercadolibretest.presentation.detail.ItemDetailFragment
import com.giussepr.mercadolibretest.presentation.detail.ItemDetailPresenter
import com.giussepr.mercadolibretest.presentation.detail.ItemDetailPresenterImpl
import com.giussepr.mercadolibretest.presentation.detail.ItemDetailView
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ItemDetailBindModule::class])
class ItemDetailModule {

    @FragmentScope
    @Provides
    fun provideHomePresenter(
        view: ItemDetailView
    ): ItemDetailPresenter {
        return ItemDetailPresenterImpl(view)
    }

}

@Module
interface ItemDetailBindModule {
    @Binds
    fun bindView(fragment: ItemDetailFragment): ItemDetailView

    @Binds fun bindFragment(fragment: ItemDetailFragment): Fragment
}
