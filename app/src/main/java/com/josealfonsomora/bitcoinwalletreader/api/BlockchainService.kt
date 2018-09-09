package com.josealfonsomora.bitcoinwalletreader.api

import com.google.gson.annotations.SerializedName
import com.josealfonsomora.bitcoinwalletreader.domain.models.Wallet
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainService {
    @GET("multiaddr")
    fun getWallet(@Query("active") xPub: String): Observable<Wallet>

}
