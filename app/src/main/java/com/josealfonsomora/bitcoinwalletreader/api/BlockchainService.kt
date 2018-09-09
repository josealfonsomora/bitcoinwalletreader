package com.josealfonsomora.bitcoinwalletreader.api

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainService {
    @GET("multiaddr")
    fun getWallet(@Query("active") xPub: String): Observable<WalletResponse>

}

data class WalletResponse(
        @SerializedName("recommend_include_fee") val recommendIncludeFee: Boolean,
        @SerializedName("info") val info: Info,
        @SerializedName("wallet") val walletInfo: WalletInfo,
        @SerializedName("addresses") val addresses: List<Address>,
        @SerializedName("txs") val transactions: List<Transaction>
)

data class Info(
        @SerializedName("nconnected") val connected: Int,
        @SerializedName("conversion") val conversion: Double,
        @SerializedName("symbol_local") val symbolLocal: Symbol,
        @SerializedName("symbol_btc") val symbolBtc: Symbol,
        @SerializedName("latest_block") val latestBlock: Block
)

data class Symbol(
        @SerializedName("code") val code: String,
        @SerializedName("symbol") val symbol: String,
        @SerializedName("name") val name: String,
        @SerializedName("conversion") val conversion: Double,
        @SerializedName("symbolAppearsAfter") val appearsAfter: Boolean,
        @SerializedName("local") val isLocal: Boolean
)

data class Block(
        @SerializedName("block_index") val index: Long,
        @SerializedName("hash") val hash: String,
        @SerializedName("height") val blockHeight: Int,
        @SerializedName("time") val blockTime: Int
)

data class Address(
        @SerializedName("address") val address: String,
        @SerializedName("n_tx") val transactions: Int,
        @SerializedName("total_received") val transactionsReceived: Int,
        @SerializedName("total_sent") val transactionsSent: Int,
        @SerializedName("final_balance") val finalBalance: Double,
        @SerializedName("gap_limit") val gpaLimit: Int,
        @SerializedName("change_index") val changeIndex: Long,
        @SerializedName("account_index") val accountIndex: Long
)

data class WalletInfo(
        @SerializedName("n_tx") val transactions: Int,
        @SerializedName("n_tx_filtered") val transactionsFiltered: Int,
        @SerializedName("total_received") val transactionsReceived: Int,
        @SerializedName("total_sent") val transactionsSent: Int,
        @SerializedName("final_balance") val finalBalance: Double)

data class Transaction(
        @SerializedName("hash") val hash: String,
        @SerializedName("ver") val version: Int,
        @SerializedName("vin_sz") val vIn: Int,
        @SerializedName("vout_sz") val vOut: Int,
        @SerializedName("size") val size: Int,
        @SerializedName("weight") val weight: Int,
        @SerializedName("fee") val fee: Double,
        @SerializedName("relayed_by") val relayedBy: String,
        @SerializedName("lock_time") val lockTime: Int,
        @SerializedName("tx_index") val index: Long,
        @SerializedName("double_spend") val doubleSpend: Boolean,
        @SerializedName("result") val result: Int,
        @SerializedName("balance") val balance: Int,
        @SerializedName("time") val time: Int,
        @SerializedName("block_height") val blockHeight: Int,
        @SerializedName("inputs") val ins: List<TransactionIn>,
        @SerializedName("out") val outs: List<TransactionOut>
)

data class TransactionIn(
        @SerializedName("prev_out") val prevOut: TransactionOut,
        @SerializedName("sequence") val sequence: Long,
        @SerializedName("script") val script: String,
        @SerializedName("witness") val witness: String
)

data class TransactionOut(
        @SerializedName("value") val value: Int,
        @SerializedName("tx_index") val index: Long,
        @SerializedName("n") val n: Int,
        @SerializedName("spent") val spent: Boolean,
        @SerializedName("script") val script: String,
        @SerializedName("type") val type: Int,
        @SerializedName("addr") val addr: String,
        @SerializedName("xpub") val xPub: XPub)

data class XPub(
        @SerializedName("m") val m: String,
        @SerializedName("path") val path: String
)
