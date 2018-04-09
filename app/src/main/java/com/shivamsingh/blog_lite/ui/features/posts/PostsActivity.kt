package com.shivamsingh.blog_lite.ui.features.posts

import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import com.shivamsingh.blog_lite.R
import com.shivamsingh.blog_lite.platform.extensions.setScreen
import com.shivamsingh.blog_lite.ui.base.BaseActivity

class PostsActivity : BaseActivity() {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    override fun layoutRes() = R.layout.activity_container

    override fun afterViews(savedInstanceState: Bundle?) {
        toolbar.setTitle(R.string.posts_title)
        showPosts()
    }

    private fun showPosts() {
        val postsFragment = PostsFragment.instance()
        observePostsFragmentEvents(postsFragment)
        setScreen(R.id.frame, postsFragment)
    }

    private fun observePostsFragmentEvents(postsFragment: PostsFragment) {
        postsFragment.postSelected.subscribe { navigator.navigateToPostDetail(this, it) }
    }
}