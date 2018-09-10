package com.josealfonsomora.bitcoinwalletreader.transactionDetail

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.josealfonsomora.bitcoinwalletreader.R
import com.josealfonsomora.bitcoinwalletreader.databinding.ActivityTransactionDetailBinding
import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import com.josealfonsomora.bitcoinwalletreader.mvp.BaseActivity
import javax.inject.Inject

class TransactionDetailActivity : BaseActivity(), TransactionDetailView {
    val transactionId: String
        get() = intent.getStringExtra(EXTRA_TRANSACTION_ID)

    private lateinit var binding: ActivityTransactionDetailBinding

    @Inject
    lateinit var viewModel: TransactionDetailViewModel

    @Inject
    lateinit var presenter: TransactionDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detail)

        binding = DataBindingUtil.bind(findViewById(R.id.container))!!
        binding.model = viewModel

    }

    override fun onStart() {
        super.onStart()
        presenter.bind(this)
    }

    override fun showTransaction(transaction: Transaction?) {
        transaction?.let { viewModel.setTransaction(it) }
    }

    companion object {
        const val EXTRA_TRANSACTION_ID = "EXTRA_TRANSACTION_ID"

        fun launch(activity: BaseActivity, transactionId: String) = activity.startActivity(
                Intent(activity, TransactionDetailActivity::class.java).apply {
                    putExtra(EXTRA_TRANSACTION_ID, transactionId)
                }
        )
    }

    override fun onStop() {
        presenter.unbind()
        super.onStop()
    }
}
