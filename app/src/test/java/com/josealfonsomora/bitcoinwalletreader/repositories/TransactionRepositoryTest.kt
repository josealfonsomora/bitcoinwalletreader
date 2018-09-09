package com.josealfonsomora.bitcoinwalletreader.repositories

import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import com.josealfonsomora.bitcoinwalletreader.storage.cache.TransactionsCache
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TransactionRepositoryTest {

    private val transactionMock: Transaction = mock()
    private val cacheMock: TransactionsCache = mock {
        on { get(any()) }.thenReturn(Single.just(transactionMock))
    }
    private val repository = TransactionRepository(transactionsCache = cacheMock)

    private val transactionId = "asdfasdfasdfasdfrandomasdfasdf"

    @Test
    fun `getTransaction returns a transactions saved in shared preferences chache storage`() {
        assertEquals(transactionMock, repository.getTransaction(transactionId).blockingGet())
    }
}
