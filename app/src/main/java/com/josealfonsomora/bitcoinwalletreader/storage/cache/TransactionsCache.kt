package com.josealfonsomora.bitcoinwalletreader.storage.cache

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.josealfonsomora.bitcoinwalletreader.api.Transaction
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionsCache @Inject constructor(val prefs: SharedPreferences) : Cache<String, Transaction> {

    private val gson = GsonBuilder().create()!!

    override fun get(key: String): Single<Transaction?> {
        val transactionJson = prefs.getString(key, "")
        val transaction = gson.fromJson(transactionJson, Transaction::class.java)
        return Single.just(transaction)
    }

    override fun set(value: Transaction) = Completable.fromAction {
        prefs.edit().putString(value.hash, gson.toJson(value, Transaction::class.java)).apply()
    }
}
