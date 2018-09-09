package com.josealfonsomora.bitcoinwalletreader.mvp

import java.lang.ref.WeakReference

open class BasePresenter<V : MvpView> : MvpPresenter<V> {
    private var weakReference: WeakReference<V>? = null

    val view: V?
        get() = weakReference?.get()

    override fun bind(view: V) {

        if (!isViewAttached) {
            weakReference = WeakReference(view)
        }
    }

    override fun unbind() {
        weakReference?.clear()
        weakReference = null
    }

    private val isViewAttached: Boolean
        get() = weakReference != null && weakReference!!.get() != null

}
