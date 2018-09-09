package com.josealfonsomora.bitcoinwalletreader.storage.cache

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import io.reactivex.Completable
import io.reactivex.Single

class TransactionsCache constructor(val prefs: SharedPreferences) {

    private val gson = GsonBuilder().create()!!

    fun get(key: String): Single<Transaction?> {
        val transactionJson = prefs.getString(key, "")
        val transaction = gson.fromJson(transactionJson, Transaction::class.java)
        return Single.just(transaction)
    }

    fun set(value: Transaction) = Completable.fromAction {
        prefs.edit().putString(value.hash, gson.toJson(value, Transaction::class.java)).apply()
    }
}
