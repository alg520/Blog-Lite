package com.shivamsingh.blog_lite.ui.features.postdetail

import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.usecase.FetchCommentsUseCase
import com.shivamsingh.blog_lite.ui.base.AbstractPresenter
import com.shivamsingh.blog_lite.ui.features.postdetail.PostDetailContract.Presenter
import com.shivamsingh.blog_lite.ui.mapper.CommentDisplayableItemMapper
import timber.log.Timber
import javax.inject.Inject

class PostDetailPresenter @Inject constructor(private val fetchCommentsUseCase: FetchCommentsUseCase,
                                              private val commentDisplayableItemMapper: CommentDisplayableItemMapper) : AbstractPresenter(), Presenter {
    private var view: PostDetailContract.View? = null

    override fun takeView(view: PostDetailContract.View) {
        this.view = view
        disposables.add(fetchCommentsUseCase)
    }

    override fun fetchComments(postId: Int) {
        view?.showLoading()
        fetchCommentsUseCase.execute({ showComments(it) }, { fetchingCommentsFailed(it) }, postId)
    }

    fun showComments(comments: List<Comment>) {
        view?.showComments(commentDisplayableItemMapper.map(comments))
        view?.hideLoading()
    }

    fun fetchingCommentsFailed(exception: Throwable) {
        Timber.e(exception)
        view?.showFetchCommentsFailed()
        view?.hideLoading()
    }
}