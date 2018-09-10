package com.josealfonsomora.bitcoinwalletreader.wallet

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.josealfonsomora.bitcoinwalletreader.R
import com.josealfonsomora.bitcoinwalletreader.databinding.ActivityWalletBinding
import com.josealfonsomora.bitcoinwalletreader.domain.models.Address
import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import com.josealfonsomora.bitcoinwalletreader.extensions.toBitcoinString
import com.josealfonsomora.bitcoinwalletreader.mvp.BaseActivity
import com.josealfonsomora.bitcoinwalletreader.transactionDetail.TransactionDetailActivity
import com.josealfonsomora.bitcoinwalletreader.wallet.adapter.AddressAdapter
import com.josealfonsomora.bitcoinwalletreader.wallet.adapter.TransactionAdapter
import javax.inject.Inject

class WalletActivity : BaseActivity(), WalletView {

    @Inject
    lateinit var presenter: WalletPresenter

    @Inject
    lateinit var viewModel: WalletViewModel

    private lateinit var binding: ActivityWalletBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        binding = DataBindingUtil.bind(findViewById(R.id.container))!!
        binding.model = viewModel

        binding.addressList.layoutManager = LinearLayoutManager(this)
        binding.addressList.setHasFixedSize(true)

        binding.transactionsList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.transactionsList.layoutManager = LinearLayoutManager(this)
        binding.transactionsList.setHasFixedSize(true)

    }

    override fun showTransactions(transactions: List<Transaction>) {
        val transactionAdapter = TransactionAdapter(this, transactions)
        transactionAdapter.clicks.subscribe {
            TransactionDetailActivity.launch(this, it.hash)
        }
        binding.transactionsList.adapter = transactionAdapter
    }

    override fun onStart() {
        super.onStart()
        presenter.bind(this)
    }

    override fun showAddresses(addresses: List<Address>) {
        binding.addressList.adapter = AddressAdapter(addresses)
    }

    override fun showBalance(finalBalance: Double) {
        viewModel.balance.set(finalBalance.toBitcoinString())
    }

    override fun onStop() {
        presenter.unbind()
        super.onStop()
    }
}
