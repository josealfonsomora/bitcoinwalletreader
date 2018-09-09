package com.josealfonsomora.bitcoinwalletreader.wallet

import android.util.Log
import com.centralway.numbrs.core.di.qualifiers.IoScheduler
import com.centralway.numbrs.core.di.qualifiers.UiScheduler
import com.josealfonsomora.bitcoinwalletreader.di.qualifiers.ActivityScoped
import com.josealfonsomora.bitcoinwalletreader.mvp.BasePresenter
import com.josealfonsomora.bitcoinwalletreader.repositories.WalletRepository
import io.reactivex.Scheduler
import javax.inject.Inject

@ActivityScoped
class WalletPresenter @Inject constructor(
        @IoScheduler private val ioScheduler: Scheduler,
        @UiScheduler private val uiScheduler: Scheduler,
        private val walletRepository: WalletRepository
) : BasePresenter<WalletView>() {

    override fun bind(view: WalletView) {
        super.bind(view)
        loadWallet()
    }

    private fun loadWallet() {
        val xPub = "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"
        walletRepository.getWallet(xPub)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(
                        {
                            it.walletInfo?.let { view?.showBalance(it.finalBalance) }
                            it.addresses?.let { view?.showAddresses(it) }
                        },
                        {
                            Log.e("WalletActivity", it.message)
                        }
                )
    }
}
