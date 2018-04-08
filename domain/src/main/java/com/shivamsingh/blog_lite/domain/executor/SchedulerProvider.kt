package com.shivamsingh.blog_lite.domain.executor

import com.aasaanjobs.partnerinternal.domain.executor.ExecutionThread
import com.aasaanjobs.partnerinternal.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulerProvider @Inject constructor(val executionThread: ExecutionThread,
                                            val postExecutionThread: PostExecutionThread) {

    fun ui(): Scheduler {
        return postExecutionThread.scheduler()
    }

    fun io(): Scheduler {
        return executionThread.scheduler()
    }

    fun computation(): Scheduler {
        return Schedulers.computation()
    }
}