package com.josealfonsomora.bitcoinwalletreader.storage.cache

import io.reactivex.Completable
import io.reactivex.Single

interface Cache<Key : Any, Value : Any> {
    fun get(key: Key): Single<Value?>
    fun set(value: Value): Completable
}
