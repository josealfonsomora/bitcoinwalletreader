package com.josealfonsomora.bitcoinwalletreader.transactionDetail

import com.centralway.numbrs.core.di.qualifiers.IoScheduler
import com.centralway.numbrs.core.di.qualifiers.TransactionId
import com.centralway.numbrs.core.di.qualifiers.UiScheduler
import com.josealfonsomora.bitcoinwalletreader.di.qualifiers.ActivityScoped
import com.josealfonsomora.bitcoinwalletreader.mvp.BasePresenter
import com.josealfonsomora.bitcoinwalletreader.repositories.TransactionRepository
import io.reactivex.Scheduler
import javax.inject.Inject

@ActivityScoped
class TransactionDetailPresenter @Inject constructor(
        @IoScheduler private val ioScheduler: Scheduler,
        @UiScheduler private val uiScheduler: Scheduler,
        private val repository: TransactionRepository,
        @TransactionId private val transactionId: String
) : BasePresenter<TransactionDetailView>() {
    override fun bind(transactionDetailView: TransactionDetailView) {
        super.bind(transactionDetailView)
        repository.getTransaction(transactionId)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe({
                    view?.showTransaction(it)
                }, {

                })
    }
}
