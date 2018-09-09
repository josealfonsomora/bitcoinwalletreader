package com.josealfonsomora.bitcoinwalletreader.repositories

import com.centralway.numbrs.core.di.qualifiers.TransactionsPreferences
import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import com.josealfonsomora.bitcoinwalletreader.storage.cache.Cache
import javax.inject.Inject

class TransactionRepository @Inject constructor(
        @TransactionsPreferences private val transactionsCache: Cache<String, Transaction>
) {
    fun getTransaction(transaction: String) = transactionsCache.get(transaction)
}
