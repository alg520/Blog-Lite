package com.shivamsingh.blog_lite.di.modules

import com.aasaanjobs.partnerinternal.di.scopes.PerApplication
import com.aasaanjobs.partnerinternal.domain.executor.ExecutionThread
import com.aasaanjobs.partnerinternal.domain.executor.PostExecutionThread
import com.shivamsingh.blog_lite.platform.executor.JobExecutor
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

@Module
class RxModule {

    @Provides
    @PerApplication
    fun executionThread(jobExecutor: JobExecutor): ExecutionThread {
        return object : ExecutionThread {
            override fun scheduler(): Scheduler = Schedulers.from(jobExecutor)
        }
    }

    @Provides
    @PerApplication
    fun postExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread;
    }
}