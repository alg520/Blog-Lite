package com.shivamsingh.blog_lite.ui.features.postdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import com.shivamsingh.blog_lite.R
import com.shivamsingh.blog_lite.platform.extensions.setScreen
import com.shivamsingh.blog_lite.ui.base.BaseActivity
import com.shivamsingh.blog_lite.ui.model.PostEntity

class PostDetailActivity : BaseActivity() {
    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    override fun layoutRes() = R.layout.activity_container

    override fun afterViews(savedInstanceState: Bundle?) {
        val post = intent.getSerializableExtra(POST) as PostEntity
        showPostDetail(post)
        setupToolbar(post)
    }

    private fun showPostDetail(post: PostEntity) {
        val postDetailFragment = PostDetailFragment.instance()
        postDetailFragment.arguments = bundle(post)
        setScreen(R.id.frame, postDetailFragment)
    }

    private fun bundle(post: PostEntity): Bundle {
        val bundle = Bundle()
        bundle.putSerializable(PostDetailFragment.POST, post)
        return bundle
    }

    private fun setupToolbar(post: PostEntity) {
        toolbar.title = post.title
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
    }

    companion object {
        const val POST = "POST"
        fun intent(context: Context) = Intent(context, PostDetailActivity::class.java)
    }
}