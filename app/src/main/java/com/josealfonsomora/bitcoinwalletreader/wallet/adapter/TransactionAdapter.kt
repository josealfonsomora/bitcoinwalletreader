package com.josealfonsomora.bitcoinwalletreader.wallet.adapter

import android.support.design.card.MaterialCardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.josealfonsomora.bitcoinwalletreader.R
import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class TransactionAdapter(private val items: List<Transaction>) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {
    private val emitter = PublishSubject.create<Transaction>()
    val clicks: Observable<Transaction> get() = emitter.hide()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items.get(position), emitter)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_transaction_adapter_item, parent, false))

    override fun getItemCount() = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val amountTextView: TextView = view.findViewById(R.id.amount)
        private val layout: MaterialCardView = view.findViewById(R.id.layout)

        fun bind(transaction: Transaction, emitter: PublishSubject<Transaction>) {
            amountTextView.text = transaction.balance.toString()
            layout.setOnClickListener {
                emitter.onNext(transaction)
            }
        }
    }
}
