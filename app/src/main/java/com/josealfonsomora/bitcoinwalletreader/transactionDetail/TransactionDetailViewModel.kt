package com.josealfonsomora.bitcoinwalletreader.transactionDetail

import android.databinding.ObservableField
import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import javax.inject.Inject

class TransactionDetailViewModel @Inject constructor() {

    val balance = ObservableField<CharSequence>("")
    val amount = ObservableField<CharSequence>("")
    val time = ObservableField<CharSequence>("")
    val transactionHash = ObservableField<CharSequence>("")

    fun setTransaction(transaction: Transaction) {
        setTransactionHash(transaction.hash)
    }

    private fun setTransactionHash(hash: String) {
        transactionHash.set(hash)
    }

}
