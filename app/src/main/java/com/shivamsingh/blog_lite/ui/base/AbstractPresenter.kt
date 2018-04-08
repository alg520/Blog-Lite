package com.shivamsingh.blog_lite.ui.base

import io.reactivex.disposables.CompositeDisposable

abstract class AbstractPresenter : PresenterLifecycle {
    var disposables = CompositeDisposable()

    override fun start() {
    }

    override fun stop() {
        disposables.dispose()
    }
}