package com.josealfonsomora.bitcoinwalletreader.wallet.adapter

import android.content.Context
import android.support.design.card.MaterialCardView
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.josealfonsomora.bitcoinwalletreader.R
import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import com.josealfonsomora.bitcoinwalletreader.extensions.toBitcoinString
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class TransactionAdapter(private val context: Context, private val items: List<Transaction>) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {
    private val emitter = PublishSubject.create<Transaction>()
    val clicks: Observable<Transaction> get() = emitter.hide()

    val redColor: Int
        get() = ContextCompat.getColor(context, R.color.redBackground)

    val greenColor: Int
        get() = ContextCompat.getColor(context, R.color.greenBackground)

    val sentText: CharSequence
        get() = context.getString(R.string.sent_label)

    val receivedText: CharSequence
        get() = context.getString(R.string.received_label)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_transaction_adapter_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = items[position]
        with(holder) {
            amountTextView.text = transaction.result.toBitcoinString()
            balanceTextView.text = transaction.balance.toBitcoinString()
            timeTextView.text = org.joda.time.DateTime(transaction.time * 1000).toString("dd/MM/yyyy HH:mm")
            val sent = transaction.result < 0
            amountTextView.setTextColor(if (sent) redColor else greenColor)
            actionTextView.text = if (sent) sentText else receivedText
            feeTextView.text = transaction.fee.toBitcoinString()

            layout.setOnClickListener {
                emitter.onNext(transaction)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val amountTextView: TextView = view.findViewById(R.id.amount)
        val timeTextView: TextView = view.findViewById(R.id.time)
        val balanceTextView: TextView = view.findViewById(R.id.balance)
        val feeTextView: TextView = view.findViewById(R.id.fees)
        val actionTextView: TextView = view.findViewById(R.id.action)
        val layout: MaterialCardView = view.findViewById(R.id.layout)
    }
}
