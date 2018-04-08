package com.shivamsingh.blog_lite.domain.usecase

import com.shivamsingh.blog_lite.domain.executor.SchedulerProvider
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.domain.repository.BlogRepository
import com.shivamsingh.blog_lite.domain.usecase.base.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(schedulerProvider: SchedulerProvider,
                                             val repository: BlogRepository)
    : FlowableUseCase<List<Post>, Unit>(schedulerProvider) {

    override fun buildUseCase(params: Unit): Flowable<List<Post>> {
        return repository.posts()
    }
}