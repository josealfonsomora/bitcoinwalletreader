package com.josealfonsomora.bitcoinwalletreader.repositories

import com.josealfonsomora.bitcoinwalletreader.api.BlockchainService
import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import com.josealfonsomora.bitcoinwalletreader.domain.models.Wallet
import com.josealfonsomora.bitcoinwalletreader.storage.cache.TransactionsCache
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WalletRepositoryTest {

    private val transactionMock: Transaction = mock()
    private val walletMock: Wallet = mock {
        on { transactions }.thenReturn(listOf(transactionMock))
    }
    private val transactionsCache: TransactionsCache = mock()
    private val serviceMock: BlockchainService = mock {
        on { getWallet(any()) }.thenReturn(Observable.just(walletMock))
    }

    private val repository = WalletRepository(
            service = serviceMock,
            cache = transactionsCache
    )

    private val xPub = "xPub1234df2134ef34rfe4rfwerfw34ferlkj3kl43lk4j5l3k4j5lk34jkl5"

    @Test
    fun `getWallet connects with service and returns a wallet object`() {
        assertEquals(walletMock, repository.getWallet(xPub).blockingFirst())
    }

    @Test
    fun `saves transactions in cache`() {
        repository.getWallet(xPub).blockingFirst()
        verify(transactionsCache).set(transactionMock)
    }
}
