package com.josealfonsomora.bitcoinwalletreader.wallet

import com.josealfonsomora.bitcoinwalletreader.api.Address
import com.josealfonsomora.bitcoinwalletreader.api.WalletInfo
import com.josealfonsomora.bitcoinwalletreader.api.Wallet
import com.josealfonsomora.bitcoinwalletreader.repositories.WalletRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.Test

class WalletPresenterTest {
    private val walletBalance: Double = 1234.0
    private val walletAddressesList: List<Address> = emptyList()

    private val walletInfoMock: WalletInfo = mock {
        on { finalBalance }.thenReturn(walletBalance)
    }
    private val view: WalletView = mock()

    private val walletResponseMock: Wallet = mock {
        on { walletInfo }.thenReturn(walletInfoMock)
        on { addresses }.thenReturn(walletAddressesList)
    }
    private val walletRepository: WalletRepository = mock {
        on { getWallet(any()) }.thenReturn(Observable.just(walletResponseMock))
    }
    private val presenter = WalletPresenter(
            uiScheduler = Schedulers.trampoline(),
            ioScheduler = Schedulers.trampoline(),
            walletRepository = walletRepository
    )

    @Test
    fun `wallet details are loaded when presenter is bind`() {
        presenter.bind(view)

        verify(view).showAddresses(walletAddressesList)
        verify(view).showBalance(walletBalance)
    }
}
