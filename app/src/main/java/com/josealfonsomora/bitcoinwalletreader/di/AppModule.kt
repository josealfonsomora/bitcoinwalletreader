package com.josealfonsomora.bitcoinwalletreader.di

import android.content.Context
import android.content.SharedPreferences
import com.centralway.numbrs.core.di.qualifiers.IoScheduler
import com.centralway.numbrs.core.di.qualifiers.UiScheduler
import com.josealfonsomora.bitcoinwalletreader.MainApplication
import com.josealfonsomora.bitcoinwalletreader.storage.cache.TransactionsCache
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: MainApplication) = application.applicationContext

    @Singleton
    @Provides
    fun providesOkhttp() = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("https://blockchain.info")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @IoScheduler
    @Singleton
    @Provides
    fun provideIoScheduler() = Schedulers.io()

    @UiScheduler
    @Singleton
    @Provides
    fun provideUiScheduler() = AndroidSchedulers.mainThread()


    @Singleton
    @Provides
    fun provideTransactionsPreferences(context: Context) =
            context.getSharedPreferences("cache_transactions", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesTransactionsCache(preferences: SharedPreferences) = TransactionsCache(prefs = preferences)
}
