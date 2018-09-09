package com.josealfonsomora.bitcoinwalletreader.repositories

import com.josealfonsomora.bitcoinwalletreader.storage.cache.TransactionsCache
import javax.inject.Inject

class TransactionRepository @Inject constructor(
        private val transactionsCache: TransactionsCache
) {
    fun getTransaction(transaction: String) = transactionsCache.get(transaction)
}
