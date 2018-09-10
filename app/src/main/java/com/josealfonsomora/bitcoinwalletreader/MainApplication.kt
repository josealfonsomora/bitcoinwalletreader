package com.josealfonsomora.bitcoinwalletreader

import com.josealfonsomora.bitcoinwalletreader.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.danlew.android.joda.JodaTimeAndroid

class MainApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this);
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
