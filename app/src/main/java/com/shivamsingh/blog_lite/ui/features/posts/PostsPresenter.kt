package com.shivamsingh.blog_lite.ui.features.posts

import com.shivamsingh.blog_lite.domain.usecase.GetAllPostsUseCase
import com.shivamsingh.blog_lite.ui.base.AbstractPresenter
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.Presenter
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.View
import timber.log.Timber
import javax.inject.Inject

class PostsPresenter @Inject constructor(val allPostsUseCase: GetAllPostsUseCase) : AbstractPresenter(), Presenter {
    private var view: View? = null

    override fun takeView(view: View) {
        this.view = view
        disposables.add(allPostsUseCase)
    }

    override fun loadPosts() {
        allPostsUseCase.execute({ view?.showPosts(it) }, { Timber.e(it) }, Unit)
    }
}