package com.shivamsingh.blog_lite.domain.usecase

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.shivamsingh.blog_lite.domain.executor.SchedulerProvider
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.domain.repository.BlogRepository
import com.shivamsingh.blog_lite.util.BaseTest
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.Collections.singletonList
import com.nhaarman.mockito_kotlin.mock

class FetchPostsUseCaseTest : BaseTest() {

    @Mock
    private lateinit var repository: BlogRepository

    private val schedulerProvider = mock<SchedulerProvider> {
        on { ui() } doReturn Schedulers.trampoline()
        on { io() } doReturn Schedulers.trampoline()
    }

    private lateinit var fetchPostsUseCase: FetchPostsUseCase

    @Before
    fun setUp() {
        fetchPostsUseCase = FetchPostsUseCase(schedulerProvider, repository)
    }

    @Test
    fun `fetch posts execution should invoke success callback when repository successfully returns posts`() {
        // Arrange
        val posts = singletonList(mock(Post::class.java))
        val successCallback = mock<(List<Post>) -> Unit> { }
        val errorCallback = mock<(Throwable) -> Unit> { }

        `when`(repository.posts()).thenReturn(Single.just(posts))

        // When
        fetchPostsUseCase.execute(successCallback, errorCallback, Unit)

        // Then
        verify(successCallback).invoke(posts)
    }

    @Test
    fun `fetch posts execution should invoke error callback when repository throws any exception`() {
        // Arrange
        val throwable = mock<Throwable> { }
        val successCallback = mock<(List<Post>) -> Unit> { }
        val errorCallback = mock<(Throwable) -> Unit> { }

        `when`(repository.posts()).thenReturn(Single.error(throwable))

        // When
        fetchPostsUseCase.execute(successCallback, errorCallback, Unit)

        // Then
        verify(errorCallback).invoke(throwable)
    }
}