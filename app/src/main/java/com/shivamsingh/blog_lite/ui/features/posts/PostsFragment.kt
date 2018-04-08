package com.shivamsingh.blog_lite.ui.features.posts

import android.os.Bundle
import com.shivamsingh.blog_lite.R
import com.shivamsingh.blog_lite.ui.base.BaseFragment
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.Presenter
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.View
import com.shivamsingh.blog_lite.ui.features.posts.entity.PostViewEntity
import javax.inject.Inject

class PostsFragment : BaseFragment(), View {

    private lateinit var presenter: PostsContract.Presenter

    override fun layoutRes() = R.layout.posts

    @Inject
    override fun attachPresenter(presenter: Presenter) {
        this.presenter = presenter
        this.presenter.takeView(this)
    }

    override fun showPosts(posts: List<PostViewEntity>) {
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
    }

    override fun onDestroy() {
        presenter.stop()
        super.onDestroy()
    }

    companion object {
        fun instance() = PostsFragment()
    }
}