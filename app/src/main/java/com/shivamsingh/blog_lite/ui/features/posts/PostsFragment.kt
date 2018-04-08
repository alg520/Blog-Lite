package com.shivamsingh.blog_lite.ui.features.posts

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.aasaanjobs.partnerinternal.recyclerview.RecyclerViewAdapter
import com.shivamsingh.blog_lite.R
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.ui.base.BaseFragment
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.Presenter
import com.shivamsingh.blog_lite.ui.features.posts.PostsContract.View
import javax.inject.Inject

class PostsFragment : BaseFragment(), View {
    @Inject
    lateinit var adapter: RecyclerViewAdapter<Post>

    @BindView(R.id.refresh)
    lateinit var refresh: SwipeRefreshLayout
    @BindView(R.id.posts)
    lateinit var posts: RecyclerView

    private lateinit var presenter: PostsContract.Presenter

    override fun layoutRes() = R.layout.posts

    @Inject
    override fun attachPresenter(presenter: Presenter) {
        this.presenter = presenter
        this.presenter.takeView(this)
    }

    override fun showLoading() {
        refresh.isRefreshing = true
    }

    override fun hideLoading() {
        refresh.isRefreshing = false
    }

    override fun showPosts(posts: List<DisplayableItem<Post>>) {
        adapter.update(posts.toMutableList())
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()

        init()
    }

    private fun init() {
        presenter.fetchPosts()
        configureRecyclerView()
        refresh.setOnRefreshListener { presenter.fetchPosts() }
    }

    private fun configureRecyclerView() {
        posts.setHasFixedSize(true)
        posts.adapter = adapter
        adapter.onViewHolderCreated { subscribeToPostEvents(it as PostCardHolder) }
    }

    private fun subscribeToPostEvents(postCardHolder: PostCardHolder) {
        postCardHolder.postSelected.subscribe { openPost(it) }
    }

    private fun openPost(post: Post) {
    }

    override fun onDestroy() {
        presenter.stop()
        super.onDestroy()
    }

    companion object {
        fun instance() = PostsFragment()
    }
}