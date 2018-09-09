package com.josealfonsomora.bitcoinwalletreader.repositories

import com.josealfonsomora.bitcoinwalletreader.api.BlockchainService
import javax.inject.Inject

class WalletRepository @Inject constructor(
        private val service: BlockchainService
) {
    fun getWallet(xPub: String) = service.getWallet(xPub)
}
