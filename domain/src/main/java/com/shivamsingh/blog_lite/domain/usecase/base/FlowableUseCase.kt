package com.shivamsingh.blog_lite.domain.usecase.base

import com.shivamsingh.blog_lite.domain.executor.SchedulerProvider
import io.reactivex.Flowable

abstract class FlowableUseCase<T, Params> constructor(val schedulerProvider: SchedulerProvider) : BaseUseCase<T>() {

    abstract fun buildUseCase(params: Params): Flowable<T>

    fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit = {}, params: Params) {
        val flowable = buildUseCase(params)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        val disposable = flowable
                .subscribeWith(disposableSubscriber(onNext, onError))
        disposables.add(disposable)
    }

}