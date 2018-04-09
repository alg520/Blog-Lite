package com.shivamsingh.blog_lite.ui.features.postdetail

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.shivamsingh.blog_lite.ui.base.BasePresenter
import com.shivamsingh.blog_lite.ui.base.BaseView
import com.shivamsingh.blog_lite.ui.base.PresenterLifecycle
import com.shivamsingh.blog_lite.ui.model.CommentEntity

interface PostDetailContract {
    interface Presenter : BasePresenter<View>, PresenterLifecycle {

        fun fetchComments(postId: Int)
    }

    interface View : BaseView<Presenter> {

        fun showComments(comments: List<DisplayableItem<CommentEntity>>)
    }
}