package com.giussepr.mercadolibretest.di

import android.app.Application
import android.content.Context
import com.giussepr.mercadolibretest.presentation.util.ConnectivityStatusManager
import com.giussepr.mercadolibretest.presentation.util.GlideImageLoader
import com.giussepr.mercadolibretest.presentation.util.KeyboardManager
import com.giussepr.mercadolibretest.presentation.util.ResourcesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideResourcesManager(context: Context): ResourcesManager {
        return ResourcesManager(context)
    }

    @Singleton
    @Provides
    fun provideGlideImageLoader(context: Context): GlideImageLoader {
        return GlideImageLoader(context)
    }

    @Singleton
    @Provides
    fun provideKeyboardManager(context: Context) : KeyboardManager {
        return KeyboardManager(context)
    }

    @Singleton
    @Provides
    fun provideConnectivityStatusManager(context: Context): ConnectivityStatusManager {
        return ConnectivityStatusManager(context)
    }
}
