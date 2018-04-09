package com.shivamsingh.blog_lite.domain.usecase

import com.shivamsingh.blog_lite.domain.executor.SchedulerProvider
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.repository.BlogRepository
import com.shivamsingh.blog_lite.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class FetchCommentsUseCase @Inject constructor(schedulerProvider: SchedulerProvider,
                                               val repository: BlogRepository)
    : SingleUseCase<List<Comment>, Int>(schedulerProvider) {

    override fun buildUseCase(postId: Int): Single<List<Comment>> {
        return repository.comments(postId)
    }
}