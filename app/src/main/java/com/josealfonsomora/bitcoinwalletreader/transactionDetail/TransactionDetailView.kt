package com.josealfonsomora.bitcoinwalletreader.transactionDetail

import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import com.josealfonsomora.bitcoinwalletreader.mvp.MvpView

interface TransactionDetailView : MvpView {
    fun showTransaction(transaction: Transaction?)
}
