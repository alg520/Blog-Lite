package com.shivamsingh.blog_lite.data.repository

import com.nhaarman.mockito_kotlin.verify
import com.shivamsingh.blog_lite.data.BlogDatabase
import com.shivamsingh.blog_lite.data.mapper.CommentMapper
import com.shivamsingh.blog_lite.data.mapper.PostMapper
import com.shivamsingh.blog_lite.data.source.BlogRemoteSource
import com.shivamsingh.blog_lite.data.source.remote.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.remote.dto.PostDto
import com.shivamsingh.blog_lite.data.source.remote.dto.UserDto
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.util.BaseTest
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.Collections.singletonList

class BlogRepositoryImplTest : BaseTest() {

    @Mock
    private lateinit var remoteSource: BlogRemoteSource
    @Mock
    private lateinit var postMapper: PostMapper
    @Mock
    private lateinit var commentMapper: CommentMapper

    private lateinit var repository: BlogRepositoryImpl

    @Before
    fun setUp() {
        repository = BlogRepositoryImpl(remoteSource, postMapper, commentMapper)
    }

    @Test
    fun `fetching posts should invoke posts,users and comments on remotesource and map on post mapper`() {
        // Arrange
        val postDtos = listOf(mock(PostDto::class.java))
        val userDtos = listOf(mock(UserDto::class.java))
        val commentDtos = listOf(mock(CommentDto::class.java))

        val postDtosSingle = Single.just(postDtos)
        val userDtosSingle = Single.just(userDtos)
        val commentDtosSingle = Single.just(commentDtos)

        val posts = singletonList(mock(Post::class.java))
        val blogDatabase = BlogDatabase(postDtos, userDtos, commentDtos)

        `when`(postMapper.map(blogDatabase)).thenReturn(posts)
        `when`(remoteSource.posts()).thenReturn(postDtosSingle)
        `when`(remoteSource.users()).thenReturn(userDtosSingle)
        `when`(remoteSource.comments()).thenReturn(commentDtosSingle)

        // When
        repository.posts().subscribe()

        // Then
        verify(remoteSource).posts()
        verify(remoteSource).users()
        verify(remoteSource).comments()
        verify(postMapper).map(blogDatabase)
    }

    @Test
    fun `fetching comments should invoke comments on remotesource and map on commentmapper`() {
        // Arrange
        val postId = 1
        val commentDtos = rawComments(postId)
        val commentDtosSingle = Single.just(commentDtos)

        val comments = singletonList(mock(Comment::class.java))

        `when`(commentMapper.map(commentDtos)).thenReturn(comments)
        `when`(remoteSource.comments()).thenReturn(commentDtosSingle)

        // When
        repository.comments(postId).subscribe()

        // Then
        verify(remoteSource).comments()
        verify(commentMapper).map(commentDtos)
    }

    private fun rawComments(postId: Int): List<CommentDto> {
        return listOf(
                CommentDto(1, postId, "Shivam Singh", "shvmsngh91@gmail.com", "test comment 1"),
                CommentDto(2, postId, "Tirupati Balan", "tirupati17@gmail.com", "test comment 2"),
                CommentDto(3, postId, "Shekhar Gupta", "shekhargupat@gmail.com", "test comment 3")
        )
    }

}