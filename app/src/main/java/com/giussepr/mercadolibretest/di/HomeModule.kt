package com.giussepr.mercadolibretest.di

import androidx.fragment.app.Fragment
import com.giussepr.mercadolibretest.presentation.home.HomeFragment
import com.giussepr.mercadolibretest.presentation.home.HomePresenter
import com.giussepr.mercadolibretest.presentation.home.HomePresenterImpl
import com.giussepr.mercadolibretest.presentation.home.HomeView
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [HomeBindModule::class])
class HomeModule {

    @Provides
    fun provideHomePresenter(view: HomeView): HomePresenter {
        return HomePresenterImpl(view)
    }

}

@Module
interface HomeBindModule {
    @Binds
    fun bindView(fragment: HomeFragment): HomeView

    @Binds fun bindFragment(fragment: HomeFragment): Fragment
}
