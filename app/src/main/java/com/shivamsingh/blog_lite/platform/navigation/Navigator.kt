package com.shivamsingh.blog_lite.platform.navigation

import android.content.Context
import com.shivamsingh.blog_lite.ui.features.postdetail.PostDetailActivity
import com.shivamsingh.blog_lite.ui.model.PostEntity
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun navigateToPosts(context: Context) {
        TODO("Not Implemented")
    }

    fun navigateToPostDetail(context: Context, post: PostEntity) {
        val intentToLaunch = PostDetailActivity.intent(context)
        intentToLaunch.putExtra(PostDetailActivity.POST, post)
        context.startActivity(intentToLaunch)
    }
}