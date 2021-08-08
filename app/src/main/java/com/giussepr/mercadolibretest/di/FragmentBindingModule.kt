package com.giussepr.mercadolibretest.di

import com.giussepr.mercadolibretest.di.scope.FragmentScope
import com.giussepr.mercadolibretest.presentation.detail.ItemDetailFragment
import com.giussepr.mercadolibretest.presentation.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ItemDetailModule::class])
    abstract fun contributeItemDetailFragment(): ItemDetailFragment

}
