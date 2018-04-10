package com.shivamsingh.blog_lite.ui.features.posts

import com.shivamsingh.blog_lite.domain.usecase.FetchPostsUseCase
import com.shivamsingh.blog_lite.ui.mapper.PostMapper
import com.shivamsingh.blog_lite.util.BaseTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class PostsPresenterTest : BaseTest() {

    @Mock
    private lateinit var fetchPostsUseCase: FetchPostsUseCase
    @Mock
    private lateinit var postMapper: PostMapper

    private lateinit var postsPresenter: PostsPresenter

    @Before
    fun setup() {
        postsPresenter = PostsPresenter(fetchPostsUseCase, postMapper)
    }

    @Test
    fun takeView() {
        // Arrange

        // When

        // Then

    }

    @Test
    fun fetchPosts() {
    }

    @Test
    fun onPostSelection() {
    }

    @Test
    fun getFetchPostsUseCase() {
    }

    @Test
    fun getPostMapper() {
    }
}