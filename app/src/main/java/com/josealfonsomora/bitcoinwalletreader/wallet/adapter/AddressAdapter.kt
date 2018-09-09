package com.josealfonsomora.bitcoinwalletreader.wallet.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.josealfonsomora.bitcoinwalletreader.R
import com.josealfonsomora.bitcoinwalletreader.domain.models.Address

class AddressAdapter(private val items: List<Address>) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items.get(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_address_adapter_item, parent, false))

    override fun getItemCount() = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val addressTextView: TextView = view.findViewById(R.id.address)
        private val balanceTextView: TextView = view.findViewById(R.id.balance)

        fun bind(address: Address) {
            addressTextView.text = address.address
            balanceTextView.text = address.finalBalance.toString()
        }
    }
}
