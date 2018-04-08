package com.aasaanjobs.partnerinternal.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {

    fun scheduler(): Scheduler
}