package com.shivamsingh.blog_lite.domain.usecase

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.shivamsingh.blog_lite.domain.executor.SchedulerProvider
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.repository.BlogRepository
import com.shivamsingh.blog_lite.util.BaseTest
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.util.*
import java.util.Collections.singletonList

class FetchCommentsUseCaseTest : BaseTest() {
    @Mock
    private lateinit var repository: BlogRepository

    private val schedulerProvider = mock<SchedulerProvider> {
        on { ui() } doReturn Schedulers.trampoline()
        on { io() } doReturn Schedulers.trampoline()
    }

    private lateinit var fetchCommentsUseCase: FetchCommentsUseCase

    @Before
    fun setUp() {
        fetchCommentsUseCase = FetchCommentsUseCase(schedulerProvider, repository)
    }

    @Test
    fun `fetch comments execution should invoke success callback when repository successfully returns comments`() {
        // Arrange
        val postId = 1
        val comments = singletonList(mock(Comment::class.java))
        val successCallback = mock<(List<Comment>) -> Unit> { }
        val errorCallback = mock<(Throwable) -> Unit> { }

        Mockito.`when`(repository.comments(postId)).thenReturn(Single.just(comments))

        // When
        fetchCommentsUseCase.execute(successCallback, errorCallback, postId)

        // Then
        verify(successCallback).invoke(comments)
    }

    @Test
    fun `fetch comments execution should invoke error callback when repository throws any exception`() {
        // Arrange
        val postId = 1
        val throwable = mock<Throwable> { }
        val successCallback = mock<(List<Comment>) -> Unit> { }
        val errorCallback = mock<(Throwable) -> Unit> { }

        Mockito.`when`(repository.comments(postId)).thenReturn(Single.error(throwable))

        // When
        fetchCommentsUseCase.execute(successCallback, errorCallback, postId)

        // Then
        verify(errorCallback).invoke(throwable)
    }
}