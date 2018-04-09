package com.shivamsingh.blog_lite.ui.features.posts

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.ui.base.BasePresenter
import com.shivamsingh.blog_lite.ui.base.BaseView
import com.shivamsingh.blog_lite.ui.base.PresenterLifecycle
import com.shivamsingh.blog_lite.ui.model.PostEntity

interface PostsContract {

    interface Presenter : BasePresenter<View>, PresenterLifecycle {

        fun fetchPosts()

        fun onPostSelection(post: PostEntity)
    }

    interface View : BaseView<Presenter> {

        fun showPosts(posts: List<DisplayableItem<PostEntity>>)

        fun viewPost(post: PostEntity)
    }
}