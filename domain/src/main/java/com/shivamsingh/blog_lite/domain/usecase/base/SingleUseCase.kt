package com.shivamsingh.blog_lite.domain.usecase.base

import com.shivamsingh.blog_lite.domain.executor.SchedulerProvider
import io.reactivex.Single

abstract class SingleUseCase<T, Params>(val schedulerProvider: SchedulerProvider) : BaseUseCase<T>() {

    abstract fun buildUseCase(params: Params): Single<T>

    fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit = {}, params: Params) {
        val single = buildUseCase(params)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        val disposable = single
                .subscribeWith(disposableSingleObserver(onNext, onError))
        disposables.add(disposable)
    }
}