package com.shivamsingh.blog_lite.di.modules

import com.aasaanjobs.partnerinternal.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UiThread @Inject internal constructor() : PostExecutionThread {

    override fun scheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}