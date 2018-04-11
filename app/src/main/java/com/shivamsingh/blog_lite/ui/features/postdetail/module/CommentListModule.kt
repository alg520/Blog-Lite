package com.shivamsingh.blog_lite.ui.features.posts.module

import com.aasaanjobs.partnerinternal.recyclerview.ItemComparator
import com.aasaanjobs.partnerinternal.recyclerview.RecyclerViewAdapter
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderBinder
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderFactory
import com.shivamsingh.blog_lite.ui.features.COMMENT_ITEM
import com.shivamsingh.blog_lite.ui.features.postdetail.CommentCardHolderBinder
import com.shivamsingh.blog_lite.ui.features.postdetail.CommentCardHolderFactory
import com.shivamsingh.blog_lite.ui.features.postdetail.CommentItemComparator
import com.shivamsingh.blog_lite.ui.model.CommentEntity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class CommentListModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideComparator(): ItemComparator<CommentEntity> = CommentItemComparator()

        @Provides
        @JvmStatic
        fun provideRecyclerAdapter(itemComparator: ItemComparator<CommentEntity>,
                                   factoryMap: Map<Int, @JvmSuppressWildcards ViewHolderFactory>,
                                   binderMap: Map<Int, @JvmSuppressWildcards ViewHolderBinder<CommentEntity>>)
                : RecyclerViewAdapter<CommentEntity> = RecyclerViewAdapter(itemComparator, factoryMap, binderMap)
    }

    @Binds
    @IntoMap
    @IntKey(COMMENT_ITEM)
    internal abstract fun provideCandidateViewHolderFactory(factory: CommentCardHolderFactory): ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(COMMENT_ITEM)
    internal abstract fun provideCandidateViewHolderBinder(binder: CommentCardHolderBinder): ViewHolderBinder<CommentEntity>
}