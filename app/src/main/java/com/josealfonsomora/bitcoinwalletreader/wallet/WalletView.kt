package com.josealfonsomora.bitcoinwalletreader.wallet

import com.josealfonsomora.bitcoinwalletreader.api.Address
import com.josealfonsomora.bitcoinwalletreader.mvp.MvpView

interface WalletView : MvpView {
    fun showAddresses(addresses: List<Address>)
    fun showBalance(finalBalance: Double)
}
