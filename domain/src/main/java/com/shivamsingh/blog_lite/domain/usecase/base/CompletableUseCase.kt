package com.shivamsingh.blog_lite.domain.usecase.base

import com.shivamsingh.blog_lite.domain.executor.SchedulerProvider
import io.reactivex.Completable

abstract class CompletableUseCase<T, Params>(val schedulerProvider: SchedulerProvider) : BaseUseCase<Void>() {

    abstract fun buildUseCase(params: Params): Completable

    fun execute(onCompleted: () -> Unit, onError: (Throwable?) -> Unit = {}, params: Params) {
        val completable = buildUseCase(params)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        val disposable = completable
                .subscribeWith(disposableCompletableObserver(onCompleted, onError))
        disposables.add(disposable)
    }
}