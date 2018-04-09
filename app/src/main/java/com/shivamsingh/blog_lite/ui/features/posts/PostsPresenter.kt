package com.shivamsingh.blog_lite.ui.features.posts

import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.domain.usecase.FetchPostsUseCase
import com.shivamsingh.blog_lite.platform.extensions.mapToDisplayableItems
import com.shivamsingh.blog_lite.ui.base.AbstractPresenter
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.Presenter
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.View
import com.shivamsingh.blog_lite.ui.features.posts.module.PostsListModule.Companion.POST_ITEM
import com.shivamsingh.blog_lite.ui.mapper.PostMapper
import com.shivamsingh.blog_lite.ui.model.PostEntity
import timber.log.Timber
import javax.inject.Inject

class PostsPresenter @Inject constructor(val fetchPostsUseCase: FetchPostsUseCase,
                                         val postMapper: PostMapper) : AbstractPresenter(), Presenter {
    private var view: View? = null

    override fun takeView(view: View) {
        this.view = view
        disposables.add(fetchPostsUseCase)
    }

    override fun fetchPosts() {
        view?.showLoading()
        fetchPostsUseCase.execute({ showPosts(it) }, { fetchingPostsFailed(it) }, Unit)
    }

    private fun showPosts(posts: List<Post>) {
        view?.showPosts(mapToDisplayableItems(POST_ITEM, postMapper.map(posts)))
        view?.hideLoading()
    }

    override fun onPostSelection(post: PostEntity) {
        view?.viewPost(post)
    }

    private fun fetchingPostsFailed(exception: Throwable) {
        Timber.e(exception)
        view?.hideLoading()
    }
}