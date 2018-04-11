package com.shivamsingh.blog_lite.ui.features.posts.module

import com.aasaanjobs.partnerinternal.recyclerview.ItemComparator
import com.aasaanjobs.partnerinternal.recyclerview.RecyclerViewAdapter
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderBinder
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderFactory
import com.shivamsingh.blog_lite.ui.features.POST_ITEM
import com.shivamsingh.blog_lite.ui.features.posts.PostCardHolderBinder
import com.shivamsingh.blog_lite.ui.features.posts.PostCardHolderFactory
import com.shivamsingh.blog_lite.ui.features.posts.PostItemComparator
import com.shivamsingh.blog_lite.ui.model.PostEntity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class PostsListModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideComparator(): ItemComparator<PostEntity> = PostItemComparator()

        @Provides
        @JvmStatic
        fun provideRecyclerAdapter(itemComparator: ItemComparator<PostEntity>,
                                   factoryMap: Map<Int, @JvmSuppressWildcards ViewHolderFactory>,
                                   binderMap: Map<Int, @JvmSuppressWildcards ViewHolderBinder<PostEntity>>)
                : RecyclerViewAdapter<PostEntity> = RecyclerViewAdapter(itemComparator, factoryMap, binderMap)
    }

    @Binds
    @IntoMap
    @IntKey(POST_ITEM)
    internal abstract fun provideCandidateViewHolderFactory(factory: PostCardHolderFactory): ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(POST_ITEM)
    internal abstract fun provideCandidateViewHolderBinder(binder: PostCardHolderBinder): ViewHolderBinder<PostEntity>
}