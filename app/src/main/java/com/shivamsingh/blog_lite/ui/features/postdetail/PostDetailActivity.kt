package com.shivamsingh.blog_lite.ui.features.postdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import com.shivamsingh.blog_lite.R
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.platform.extensions.setScreen
import com.shivamsingh.blog_lite.ui.base.BaseActivity

class PostDetailActivity : BaseActivity() {
    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    override fun layoutRes() = R.layout.activity_container

    override fun afterViews(savedInstanceState: Bundle?) {
        toolbar.setTitle(R.string.posts_title)
        showPostDetail(intent.getSerializableExtra(POST) as Post)
    }

    private fun showPostDetail(post: Post) {
        val postDetailFragment = PostDetailFragment.instance()
        postDetailFragment.arguments = bundle(post)
        setScreen(R.id.frame, postDetailFragment)
    }

    private fun bundle(post: Post): Bundle {
        val bundle = Bundle()
        bundle.putSerializable(PostDetailFragment.POST, post)
        return bundle
    }

    companion object {
        const val POST = "POST"
        fun intent(context: Context) = Intent(context, PostDetailActivity::class.java)
    }
}