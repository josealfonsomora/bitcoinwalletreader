package com.josealfonsomora.bitcoinwalletreader.di

import com.josealfonsomora.bitcoinwalletreader.di.qualifiers.ActivityScoped
import com.josealfonsomora.bitcoinwalletreader.wallet.WalletActivity
import com.josealfonsomora.bitcoinwalletreader.wallet.WalletModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DaggerBinding {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [WalletModule::class])
    internal abstract fun walletActivity(): WalletActivity
}
