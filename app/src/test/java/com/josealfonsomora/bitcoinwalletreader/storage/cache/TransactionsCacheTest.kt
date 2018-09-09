package com.josealfonsomora.bitcoinwalletreader.storage.cache

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.josealfonsomora.bitcoinwalletreader.domain.models.Transaction
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TransactionsCacheTest {

    private val gson = GsonBuilder().create()!!

    private val transaction = Transaction(
            hash = "2db21dc260e26efb631d1a63d16bf581e9d6eccad4807f79af674b12e83e3725",
            version = 1,
            vIn = 1,
            vOut = 2,
            size = 226,
            weight = 904,
            fee = 113.0,
            relayedBy = "127.0.0.1",
            lockTime = 0,
            index = 364541597,
            doubleSpend = false,
            result = 1364714,
            balance = 1384642,
            time = 1533264144,
            blockHeight = 534937,
            ins = emptyList(),
            outs = emptyList())

    private val transactionjson = gson.toJson(transaction, Transaction::class.java)

    private val preferencesEditorMock: SharedPreferences.Editor = mock {
        on { putString(any(), any()) }.thenReturn(it)
    }
    private val preferencesMock: SharedPreferences = mock {
        on { edit() }.thenReturn(preferencesEditorMock)
        on { getString(any(), any()) }.thenReturn(transactionjson)
    }
    private val cache = TransactionsCache(prefs = preferencesMock)


    @Test
    fun `get returns value from as Transaction object shared preferences`() {
        val cachedTransaction = cache.get(transaction.hash).blockingGet()

        verify(preferencesMock).getString(transaction.hash, "")

        assertEquals(transaction, cachedTransaction)
    }

    @Test
    fun `set saves Transaction object as json string in shared preferences`() {
        cache.set(transaction)

        verify(preferencesEditorMock).putString(transaction.hash, transactionjson)
    }

}

