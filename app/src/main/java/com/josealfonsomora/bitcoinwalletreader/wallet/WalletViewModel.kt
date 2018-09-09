package com.josealfonsomora.bitcoinwalletreader.wallet

import android.databinding.ObservableField
import com.josealfonsomora.bitcoinwalletreader.di.qualifiers.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class WalletViewModel @Inject constructor() {
    val balance = ObservableField<CharSequence>("")
}
