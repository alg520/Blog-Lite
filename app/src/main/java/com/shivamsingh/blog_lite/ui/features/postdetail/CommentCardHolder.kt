package com.shivamsingh.blog_lite.ui.features.postdetail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.aasaanjobs.partnerinternal.di.qualifiers.ForApplication
import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderBinder
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderFactory
import com.shivamsingh.blog_lite.R
import com.shivamsingh.blog_lite.ui.model.CommentEntity
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CommentCardHolder(itemView: View, val picasso: Picasso) : RecyclerView.ViewHolder(itemView) {
    private lateinit var comment: CommentEntity

    @BindView(R.id.pic)
    lateinit var pic: ImageView

    @BindView(R.id.name)
    lateinit var name: TextView

    @BindView(R.id.email)
    lateinit var email: TextView

    @BindView(R.id.comment)
    lateinit var commentBody: TextView

    fun bind(comment: CommentEntity) {
        name.text = comment.name
        email.text = comment.email
        commentBody.text = comment.body
        picasso.load(comment.avatar).into(pic)
    }
}

class CommentCardHolderFactory @Inject constructor(@ForApplication context: Context, val picasso: Picasso)
    : ViewHolderFactory(context) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val postView = LayoutInflater.from(context).inflate(R.layout.comment_card, parent, false)
        val postCardHolder = CommentCardHolder(postView, picasso)
        ButterKnife.bind(postCardHolder, postView)
        return postCardHolder
    }
}

class CommentCardHolderBinder @Inject constructor() : ViewHolderBinder<CommentEntity> {
    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<CommentEntity>) {
        (viewHolder as CommentCardHolder).bind(item.model)
    }
}