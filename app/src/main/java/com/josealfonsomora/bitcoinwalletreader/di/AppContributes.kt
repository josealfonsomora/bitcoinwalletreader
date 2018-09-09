package com.josealfonsomora.bitcoinwalletreader.di

import com.josealfonsomora.bitcoinwalletreader.di.qualifiers.ActivityScoped
import com.josealfonsomora.bitcoinwalletreader.transactionDetail.TransactionDetailActivity
import com.josealfonsomora.bitcoinwalletreader.transactionDetail.di.TransactionDetailModule
import com.josealfonsomora.bitcoinwalletreader.wallet.WalletActivity
import com.josealfonsomora.bitcoinwalletreader.wallet.di.WalletModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppContributes {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [WalletModule::class])
    abstract fun walletActivity(): WalletActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TransactionDetailModule::class])
    abstract fun transactionDetail(): TransactionDetailActivity

}
