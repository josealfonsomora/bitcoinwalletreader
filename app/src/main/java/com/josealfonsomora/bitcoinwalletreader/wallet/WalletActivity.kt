package com.josealfonsomora.bitcoinwalletreader.wallet

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.josealfonsomora.bitcoinwalletreader.R
import com.josealfonsomora.bitcoinwalletreader.api.Address
import com.josealfonsomora.bitcoinwalletreader.databinding.ActivityWalletBinding
import com.josealfonsomora.bitcoinwalletreader.wallet.adapter.AddressAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WalletActivity : DaggerAppCompatActivity(), WalletView {

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
    }

    override fun onStart() {
        super.onStart()
        presenter.bind(this)
    }

    override fun showAddresses(addresses: List<Address>) {
        binding.addressList.adapter = AddressAdapter(addresses)
    }

    override fun showBalance(finalBalance: Double) {
        viewModel.balance.set(finalBalance.toString())
    }

    override fun onStop() {
        presenter.unbind()
        super.onStop()
    }
}
