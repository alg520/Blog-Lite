package com.shivamsingh.blog_lite.ui.features.posts

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.domain.usecase.FetchPostsUseCase
import com.shivamsingh.blog_lite.ui.mapper.PostDisplayableItemMapper
import com.shivamsingh.blog_lite.ui.model.PostEntity
import com.shivamsingh.blog_lite.util.BaseTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.Collections.singletonList

class PostsPresenterTest : BaseTest() {

    @Mock
    private lateinit var fetchPostsUseCase: FetchPostsUseCase
    @Mock
    private lateinit var postDisplayableItemMapper: PostDisplayableItemMapper
    @Mock
    private lateinit var view: PostsContract.View

    private lateinit var postsPresenter: PostsPresenter

    @Before
    fun setup() {
        postsPresenter = PostsPresenter(fetchPostsUseCase, postDisplayableItemMapper)
        postsPresenter.takeView(view)
    }

    @Test
    fun `fetch posts should call show posts on view when success callback invoked`() {
        // Arrange
        val posts = singletonList(mock(Post::class.java))
        val displayablePosts = singletonList(mock(DisplayableItem::class.java))

        val successCallback: (List<Post>) -> Unit = com.nhaarman.mockito_kotlin.mock { }

        `when`(postDisplayableItemMapper.map(posts)).thenReturn(displayablePosts as List<DisplayableItem<PostEntity>>)
        doReturn(postsPresenter.showPosts(posts)).`when`(successCallback).invoke(posts)
        `when`(fetchPostsUseCase.execute(any(), any(), any())).then { successCallback.invoke(posts) }

        // When
        postsPresenter.fetchPosts()

        // Then
        verify(view).showLoading()
        verify(view).showPosts(displayablePosts)
        verify(view).hideLoading()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `fetch posts should call fetching posts failed on view when error callback invoked`() {
        // Arrange
        val throwable = mock(Throwable::class.java)
        val errorCallback: (Throwable) -> Unit = com.nhaarman.mockito_kotlin.mock { }

        doReturn(postsPresenter.fetchingPostsFailed(throwable)).`when`(errorCallback).invoke(throwable)
        `when`(fetchPostsUseCase.execute(any(), any(), any())).then { errorCallback(throwable) }

        // When
        postsPresenter.fetchPosts()

        // Then
        verify(view).showLoading()
        verify(view).showFetchingFailed()
        verify(view).hideLoading()
        verifyNoMoreInteractions(view)
    }
}
