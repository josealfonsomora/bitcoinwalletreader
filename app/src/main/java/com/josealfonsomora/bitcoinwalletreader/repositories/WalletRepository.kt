package com.josealfonsomora.bitcoinwalletreader.repositories

import com.josealfonsomora.bitcoinwalletreader.api.BlockchainService
import com.josealfonsomora.bitcoinwalletreader.storage.cache.TransactionsCache
import javax.inject.Inject

class WalletRepository @Inject constructor(
        private val service: BlockchainService,
        private val cache: TransactionsCache
) {

    fun getWallet(xPub: String) =
            service.getWallet(xPub)
                    .map {
                        it.transactions.forEach { cache.set(it) }
                        it
                    }
}
