package com.shivamsingh.blog_lite.domain.usecase

import com.shivamsingh.blog_lite.domain.executor.SchedulerProvider
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.repository.BlogRepository
import com.shivamsingh.blog_lite.domain.usecase.base.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class FetchCommentsUseCase @Inject constructor(schedulerProvider: SchedulerProvider,
                                               val repository: BlogRepository)
    : FlowableUseCase<List<Comment>, Int>(schedulerProvider) {

    override fun buildUseCase(postId: Int): Flowable<List<Comment>> {
        return repository.comments(postId)
    }
}