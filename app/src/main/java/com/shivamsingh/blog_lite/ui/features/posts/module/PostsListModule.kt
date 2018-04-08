package com.shivamsingh.blog_lite.ui.features.posts.module

import com.aasaanjobs.partnerinternal.recyclerview.ItemComparator
import com.aasaanjobs.partnerinternal.recyclerview.RecyclerViewAdapter
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderBinder
import com.aasaanjobs.partnerinternal.recyclerview.ViewHolderFactory
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.ui.features.posts.PostCardHolderBinder
import com.shivamsingh.blog_lite.ui.features.posts.PostCardHolderFactory
import com.shivamsingh.blog_lite.ui.features.posts.PostItemComparator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class PostsListModule {

    @Module
    companion object {
        const val POST_ITEM = 0

        @Provides
        @JvmStatic
        fun provideComparator(): ItemComparator<Post> = PostItemComparator()

        @Provides
        @JvmStatic
        fun provideRecyclerAdapter(itemComparator: ItemComparator<Post>,
                                   factoryMap: Map<Int, @JvmSuppressWildcards ViewHolderFactory>,
                                   binderMap: Map<Int, @JvmSuppressWildcards ViewHolderBinder<Post>>)
                : RecyclerViewAdapter<Post> = RecyclerViewAdapter(itemComparator, factoryMap, binderMap)
    }

    @Binds
    @IntoMap
    @IntKey(POST_ITEM)
    internal abstract fun provideCandidateViewHolderFactory(factory: PostCardHolderFactory): ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(POST_ITEM)
    internal abstract fun provideCandidateViewHolderBinder(binder: PostCardHolderBinder): ViewHolderBinder<Post>
}