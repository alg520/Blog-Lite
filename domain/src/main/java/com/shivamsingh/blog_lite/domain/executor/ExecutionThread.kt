package com.aasaanjobs.partnerinternal.domain.executor

import io.reactivex.Scheduler

interface ExecutionThread {

    fun scheduler(): Scheduler
}