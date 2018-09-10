package com.josealfonsomora.bitcoinwalletreader.extensions

fun Double.toBitcoin() = this / 100000000

fun Double.toBitcoinString() = "%.8f".format(this.toBitcoin())

