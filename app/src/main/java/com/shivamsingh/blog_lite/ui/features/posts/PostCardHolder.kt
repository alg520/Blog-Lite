package com.shivamsingh.blog_lite.ui.features.posts

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.aasaanjobs.partnerinternal.di.qualifiers.ForApplication
import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderBinder
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderFactory
import com.shivamsingh.blog_lite.R
import com.shivamsingh.blog_lite.domain.model.Post
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class PostCardHolder(itemView: View, val picasso: Picasso) : RecyclerView.ViewHolder(itemView) {

    val postSelected = PublishSubject.create<Post>()

    private lateinit var post: Post

    @BindView(R.id.pic)
    lateinit var pic: ImageView

    @BindView(R.id.title)
    lateinit var title: TextView

    @BindView(R.id.body)
    lateinit var body: TextView

    @BindView(R.id.comments_count)
    lateinit var commentsCount: TextView

    @OnClick(R.id.post)
    fun postSelected() {
        postSelected.onNext(post)
    }

    fun bind(post: Post) {
        this.post = post
        title.text = post.title
        body.text = post.body
        commentsCount.text = itemView.context.getString(R.string.comments_count).format(post.commentsCount)
        picasso.load(post.authorPicUrl).into(pic)
    }
}

class PostCardHolderFactory @Inject constructor(@ForApplication context: Context, val picasso: Picasso)
    : ViewHolderFactory(context) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val postView = LayoutInflater.from(context).inflate(R.layout.post_card, parent, false)
        val postCardHolder = PostCardHolder(postView, picasso)
        ButterKnife.bind(postCardHolder, postView)
        return postCardHolder
    }
}

class PostCardHolderBinder @Inject constructor() : ViewHolderBinder<Post> {
    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<Post>) {
        (viewHolder as PostCardHolder).bind(item.model)
    }
}