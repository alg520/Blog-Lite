package com.shivamsingh.blog_lite.ui.features.postdetail

import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.usecase.FetchCommentsUseCase
import com.shivamsingh.blog_lite.platform.extensions.mapToDisplayableItems
import com.shivamsingh.blog_lite.ui.base.AbstractPresenter
import com.shivamsingh.blog_lite.ui.features.postdetail.PostDetailContract.Presenter
import com.shivamsingh.blog_lite.ui.features.posts.module.CommentListModule.Companion.COMMENT_ITEM
import com.shivamsingh.blog_lite.ui.mapper.CommentMapper
import timber.log.Timber
import javax.inject.Inject

class PostDetailPresenter @Inject constructor(val fetchCommentsUseCase: FetchCommentsUseCase,
                                              val commentMapper: CommentMapper) : AbstractPresenter(), Presenter {
    private var view: PostDetailContract.View? = null

    override fun takeView(view: PostDetailContract.View) {
        this.view = view
        disposables.add(fetchCommentsUseCase)
    }

    override fun fetchComments(postId: Int) {
        fetchCommentsUseCase.execute({ showComments(it) }, { fetchingCommentsFailed(it) }, postId)
    }

    private fun showComments(comments: List<Comment>) {
        view?.showComments(mapToDisplayableItems(COMMENT_ITEM, commentMapper.map(comments)))
        view?.hideLoading()
    }

    private fun fetchingCommentsFailed(exception: Throwable) {
        Timber.e(exception)
        view?.hideLoading()
    }
}