package com.giussepr.mercadolibretest.di

import android.app.Application
import com.giussepr.mercadolibretest.app.MercadoLibreTestApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    FragmentBindingModule::class,
    NetModule::class,
    RepositoryModule::class,
    MercadoLibreRemoteModule::class
])
interface ApplicationComponent: AndroidInjector<MercadoLibreTestApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
