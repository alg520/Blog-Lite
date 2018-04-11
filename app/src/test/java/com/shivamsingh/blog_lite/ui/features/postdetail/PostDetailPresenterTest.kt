package com.shivamsingh.blog_lite.ui.features.postdetail

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.usecase.FetchCommentsUseCase
import com.shivamsingh.blog_lite.ui.mapper.CommentDisplayableItemMapper
import com.shivamsingh.blog_lite.ui.model.CommentEntity
import com.shivamsingh.blog_lite.util.BaseTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import java.util.*

class PostDetailPresenterTest : BaseTest() {

    @Mock
    private lateinit var fetchCommentsUseCase: FetchCommentsUseCase
    @Mock
    private lateinit var commentDisplayableItemMapper: CommentDisplayableItemMapper
    @Mock
    private lateinit var view: PostDetailContract.View

    private lateinit var postDetailPresenter: PostDetailPresenter

    @Before
    fun setup() {
        postDetailPresenter = PostDetailPresenter(fetchCommentsUseCase, commentDisplayableItemMapper)
        postDetailPresenter.takeView(view)
    }

    @Test
    fun `fetch comments should call show comments on view when success callback invoked`() {
        // Arrange
        val postId = 1
        val comments = Collections.singletonList(Mockito.mock(Comment::class.java))
        val displayableComments = Collections.singletonList(Mockito.mock(DisplayableItem::class.java))

        val successCallback: (List<Comment>) -> Unit = com.nhaarman.mockito_kotlin.mock { }

        Mockito.`when`(commentDisplayableItemMapper.map(comments)).thenReturn(displayableComments as List<DisplayableItem<CommentEntity>>)
        doReturn(postDetailPresenter.showComments(comments)).`when`(successCallback).invoke(comments)
        Mockito.`when`(fetchCommentsUseCase.execute(any(), any(), any())).then { successCallback.invoke(comments) }

        // When
        postDetailPresenter.fetchComments(postId)

        // Then
        verify(view).showLoading()
        verify(view).showComments(displayableComments)
        verify(view).hideLoading()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `fetch comments should call fetching comments failed on view when error callback invoked`() {
        // Arrange
        val postId = 1
        val throwable = Mockito.mock(Throwable::class.java)
        val errorCallback: (Throwable) -> Unit = com.nhaarman.mockito_kotlin.mock { }

        doReturn(postDetailPresenter.fetchingCommentsFailed(throwable)).`when`(errorCallback).invoke(throwable)
        Mockito.`when`(fetchCommentsUseCase.execute(any(), any(), any())).then { errorCallback(throwable) }

        // When
        postDetailPresenter.fetchComments(postId)


        // Then
        verify(view).showLoading()
        verify(view).showFetchCommentsFailed()
        verify(view).hideLoading()
        verifyNoMoreInteractions(view)
    }

}