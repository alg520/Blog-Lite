package com.shivamsingh.blog_lite.domain.usecase

import com.shivamsingh.blog_lite.domain.executor.SchedulerProvider
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.domain.repository.BlogRepository
import com.shivamsingh.blog_lite.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(schedulerProvider: SchedulerProvider,
                                             val repository: BlogRepository)
    : SingleUseCase<List<Post>, Unit>(schedulerProvider) {

    override fun buildUseCase(params: Unit): Single<List<Post>> {
        return repository.posts()
    }
}