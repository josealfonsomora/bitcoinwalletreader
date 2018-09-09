package com.josealfonsomora.bitcoinwalletreader.transactionDetail.di

import com.centralway.numbrs.core.di.qualifiers.TransactionId
import com.josealfonsomora.bitcoinwalletreader.di.qualifiers.ActivityScoped
import com.josealfonsomora.bitcoinwalletreader.transactionDetail.TransactionDetailActivity
import dagger.Module
import dagger.Provides

@Module
class TransactionDetailModule {

    @TransactionId
    @ActivityScoped
    @Provides
    fun provideTransactionId(activity: TransactionDetailActivity) = activity.transactionId
}
