package com.josealfonsomora.bitcoinwalletreader.transactionDetail

import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import com.josealfonsomora.bitcoinwalletreader.repositories.TransactionRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.Test

class TransactionDetailPresenterTest {

    private val view: TransactionDetailView = mock()
    private val transactionId = "123asd2q3asfasdfrandomhash1asfd123sdfa"
    private val transactionMock: Transaction = mock()
    val transactionRepositoryMock: TransactionRepository = mock {
        on { getTransaction(transactionId) }.thenReturn(Single.just(transactionMock))
    }

    val presenter = TransactionDetailPresenter(
            uiScheduler = Schedulers.trampoline(),
            ioScheduler = Schedulers.trampoline(),
            repository = transactionRepositoryMock,
            transactionId = transactionId
    )

    @Test
    fun `show transaction from cache on bind`() {
        presenter.bind(view)

        verify(view).showTransaction(transactionMock)
    }
}
