package com.josealfonsomora.bitcoinwalletreader.mvp

interface MvpPresenter<V : MvpView> {
    fun bind(view: V)
    fun unbind()
}
