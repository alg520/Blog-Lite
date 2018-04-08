package com.shivamsingh.blog_lite.ui.features.posts

import android.os.Bundle
import com.shivamsingh.blog_lite.R
import com.shivamsingh.blog_lite.platform.extensions.setScreen
import com.shivamsingh.blog_lite.ui.base.BaseActivity

class PostsActivity : BaseActivity() {

    override fun layoutRes() = R.layout.activity_container


    override fun afterViews(savedInstanceState: Bundle?) {
        showPosts()
    }

    private fun showPosts() {
        setScreen(R.id.frame, PostsFragment.instance())
    }
}