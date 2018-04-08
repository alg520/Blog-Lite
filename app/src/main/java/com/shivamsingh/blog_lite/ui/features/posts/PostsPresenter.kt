package com.shivamsingh.blog_lite.ui.features.posts

import com.shivamsingh.blog_lite.ui.base.AbstractPresenter
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.Presenter
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.View
import javax.inject.Inject

class PostsPresenter @Inject constructor() : AbstractPresenter(), Presenter {
    private var view: View? = null

    override fun takeView(view: View) {
        this.view = view
    }

    override fun loadPosts() {
    }
}