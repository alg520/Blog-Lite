package com.shivamsingh.blog_lite.ui.features.posts.module

import com.aasaanjobs.partnerinternal.recyclerview.ItemComparator
import com.aasaanjobs.partnerinternal.recyclerview.RecyclerViewAdapter
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderBinder
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderFactory
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.ui.features.postdetail.CommentCardHolderBinder
import com.shivamsingh.blog_lite.ui.features.postdetail.CommentCardHolderFactory
import com.shivamsingh.blog_lite.ui.features.postdetail.CommentItemComparator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class CommentListModule {

    @Module
    companion object {
        const val COMMENT_ITEM = 1

        @Provides
        @JvmStatic
        fun provideComparator(): ItemComparator<Comment> = CommentItemComparator()

        @Provides
        @JvmStatic
        fun provideRecyclerAdapter(itemComparator: ItemComparator<Comment>,
                                   factoryMap: Map<Int, @JvmSuppressWildcards ViewHolderFactory>,
                                   binderMap: Map<Int, @JvmSuppressWildcards ViewHolderBinder<Comment>>)
                : RecyclerViewAdapter<Comment> = RecyclerViewAdapter(itemComparator, factoryMap, binderMap)
    }

    @Binds
    @IntoMap
    @IntKey(COMMENT_ITEM)
    internal abstract fun provideCandidateViewHolderFactory(factory: CommentCardHolderFactory): ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(COMMENT_ITEM)
    internal abstract fun provideCandidateViewHolderBinder(binder: CommentCardHolderBinder): ViewHolderBinder<Comment>
}