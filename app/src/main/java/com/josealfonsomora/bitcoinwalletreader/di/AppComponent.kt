package com.josealfonsomora.bitcoinwalletreader.di

import com.josealfonsomora.bitcoinwalletreader.MainApplication
import com.josealfonsomora.bitcoinwalletreader.api.ApiModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            ApiModule::class,
            DaggerBinding::class,
            AndroidSupportInjectionModule::class
        ]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}
