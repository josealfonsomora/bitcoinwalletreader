package com.josealfonsomora.bitcoinwalletreader.api

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun provideBlockainService(retrofit: Retrofit) = retrofit.create(BlockchainService::class.java)
}
