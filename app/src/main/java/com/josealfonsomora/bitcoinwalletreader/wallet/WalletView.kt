package com.josealfonsomora.bitcoinwalletreader.wallet

import com.josealfonsomora.bitcoinwalletreader.domain.models.Address
import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import com.josealfonsomora.bitcoinwalletreader.mvp.MvpView

interface WalletView : MvpView {
    fun showAddresses(addresses: List<Address>)
    fun showBalance(finalBalance: Double)
    fun showTransactions(transactions: List<Transaction>)
}
