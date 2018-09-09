package com.josealfonsomora.bitcoinwalletreader.wallet.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.josealfonsomora.bitcoinwalletreader.R
import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction

class TransactionAdapter(private val items: List<Transaction>) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items.get(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_transaction_adapter_item, parent, false))

    override fun getItemCount() = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val amountTextView: TextView = view.findViewById(R.id.amount)
        fun bind(transaction: Transaction) {
            amountTextView.text = transaction.balance.toString()
        }
    }
}
