package com.josealfonsomora.bitcoinwalletreader.mvp

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

open class BasePresenter<V : MvpView> : MvpPresenter<V> {
    private var weakReference: WeakReference<V>? = null
    val disposables = CompositeDisposable()

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
        disposables.dispose()
    }

    private val isViewAttached: Boolean
        get() = weakReference != null && weakReference!!.get() != null

}
