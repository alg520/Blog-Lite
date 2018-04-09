package com.shivamsingh.blog_lite.ui.features.postdetail

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.aasaanjobs.partnerinternal.recyclerview.RecyclerViewAdapter
import com.shivamsingh.blog_lite.R
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.ui.base.BaseFragment
import com.shivamsingh.blog_lite.ui.features.postdetail.PostDetailContract.Presenter
import com.shivamsingh.blog_lite.ui.features.postdetail.PostDetailContract.View
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import javax.inject.Inject

class PostDetailFragment : BaseFragment(), View {

    @Inject
    lateinit var adapter: RecyclerViewAdapter<Comment>

    @BindView(R.id.comments)
    lateinit var comments: RecyclerView

    private lateinit var presenter: Presenter

    override fun layoutRes() = R.layout.post_detail

    @Inject
    override fun attachPresenter(presenter: Presenter) {
        this.presenter = presenter
        this.presenter.takeView(this)
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()

        init()
    }

    private fun init() {
        val post = arguments.getSerializable(POST) as Post
        presenter.fetchComments(post.id)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        comments.addItemDecoration(divider())
        comments.setHasFixedSize(true)
        comments.adapter = adapter
    }

    private fun divider(): HorizontalDividerItemDecoration? {
        return HorizontalDividerItemDecoration.Builder(context)
                .color(resources.getColor(R.color.grey_c9))
                .sizeResId(R.dimen.divider)
                .build()
    }

    override fun onDestroy() {
        presenter.stop()
        super.onDestroy()
    }

    override fun showComments(comments: List<DisplayableItem<Comment>>) = adapter.update(comments.toMutableList())

    companion object {
        const val POST = "POST"
        fun instance() = PostDetailFragment()
    }
}