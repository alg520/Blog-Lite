package com.shivamsingh.blog_lite.data.repository

import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.shivamsingh.blog_lite.data.InMemoryBlogDatabase
import com.shivamsingh.blog_lite.data.mapper.CommentMapper
import com.shivamsingh.blog_lite.data.mapper.PostDatabaseEntityMapper
import com.shivamsingh.blog_lite.data.mapper.PostMapper
import com.shivamsingh.blog_lite.data.source.BlogRemoteSource
import com.shivamsingh.blog_lite.data.source.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.dto.PostDto
import com.shivamsingh.blog_lite.data.source.dto.UserDto
import com.shivamsingh.blog_lite.data.source.local.BlogLocalSource
import com.shivamsingh.blog_lite.data.source.local.entity.PostEntity
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.util.BaseTest
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.Collections.singletonList

class BlogRepositoryImplTest : BaseTest() {

    @Mock
    private lateinit var localSource: BlogLocalSource
    @Mock
    private lateinit var remoteSource: BlogRemoteSource
    @Mock
    private lateinit var postDatabaseEntityMapper: PostDatabaseEntityMapper
    @Mock
    private lateinit var postMapper: PostMapper
    @Mock
    private lateinit var commentMapper: CommentMapper

    private lateinit var repository: BlogRepositoryImpl

    @Before
    fun setUp() {
        repository = BlogRepositoryImpl(localSource, remoteSource, postDatabaseEntityMapper, postMapper, commentMapper)
    }

    @Test
    fun `fetching posts should invoke posts,users and comments on remotesource and mapped posts on local source and mappers`() {
        // Arrange
        val postDbEntities = listOf(mock(PostEntity::class.java))
        val postDtos = listOf(mock(PostDto::class.java))
        val userDtos = listOf(mock(UserDto::class.java))
        val commentDtos = listOf(mock(CommentDto::class.java))

        val postDbEntitiesFlowable = Flowable.just(postDbEntities)
        val postDtosSingle = Single.just(postDtos)
        val userDtosSingle = Single.just(userDtos)
        val commentDtosSingle = Single.just(commentDtos)

        val posts = singletonList(mock(Post::class.java))
        val blogDatabase = InMemoryBlogDatabase(postDtos, userDtos, commentDtos)

        `when`(postMapper.map(blogDatabase)).thenReturn(posts)
        `when`(remoteSource.posts()).thenReturn(postDtosSingle)
        `when`(remoteSource.users()).thenReturn(userDtosSingle)
        `when`(remoteSource.comments()).thenReturn(commentDtosSingle)

        `when`(postDatabaseEntityMapper.map(postDbEntities)).thenReturn(posts)
        `when`(localSource.mappedPosts()).thenReturn(postDbEntitiesFlowable)

        doNothing().`when`(localSource).savePosts(postDtos)
        doNothing().`when`(localSource).saveUsers(userDtos)
        doNothing().`when`(localSource).saveComments(commentDtos)

        // When
        repository.posts().subscribe()

        // Then
        verify(remoteSource).posts()
        verify(remoteSource).users()
        verify(remoteSource).comments()
        verify(postMapper).map(blogDatabase)
        verify(localSource).mappedPosts()
        verify(postDatabaseEntityMapper).map(postDbEntities)
    }

    @Test
    fun `fetching posts should invoke posts,users and comments on remotesource and map on post mapper`() {
        // Arrange
        val postDbEntities = listOf(mock(PostEntity::class.java))
        val postDtos = listOf(mock(PostDto::class.java))
        val userDtos = listOf(mock(UserDto::class.java))
        val commentDtos = listOf(mock(CommentDto::class.java))

        val postDbEntitiesFlowable = Flowable.just(postDbEntities)
        val postDtosSingle = Single.just(postDtos)
        val userDtosSingle = Single.just(userDtos)
        val commentDtosSingle = Single.just(commentDtos)

        val posts = singletonList(mock(Post::class.java))
        val blogDatabase = InMemoryBlogDatabase(postDtos, userDtos, commentDtos)

        `when`(postMapper.map(blogDatabase)).thenReturn(posts)
        `when`(remoteSource.posts()).thenReturn(postDtosSingle)
        `when`(remoteSource.users()).thenReturn(userDtosSingle)
        `when`(remoteSource.comments()).thenReturn(commentDtosSingle)

        `when`(postDatabaseEntityMapper.map(postDbEntities)).thenReturn(posts)
        `when`(localSource.mappedPosts()).thenReturn(postDbEntitiesFlowable)

        doNothing().`when`(localSource).savePosts(postDtos)
        doNothing().`when`(localSource).saveUsers(userDtos)
        doNothing().`when`(localSource).saveComments(commentDtos)

        // When
        repository.posts().subscribe()

        // Then
        verify(remoteSource).posts()
        verify(remoteSource).users()
        verify(remoteSource).comments()
        verify(postMapper).map(blogDatabase)
    }

    @Test
    fun `fetching posts should invoke mappedposts on localsource and map on post database entity mapper`() {
        // Arrange
        val postDbEntities = listOf(mock(PostEntity::class.java))
        val postDtos = listOf(mock(PostDto::class.java))
        val userDtos = listOf(mock(UserDto::class.java))
        val commentDtos = listOf(mock(CommentDto::class.java))

        val postDbEntitiesFlowable = Flowable.just(postDbEntities)
        val postDtosSingle = Single.just(postDtos)
        val userDtosSingle = Single.just(userDtos)
        val commentDtosSingle = Single.just(commentDtos)

        val posts = singletonList(mock(Post::class.java))
        val blogDatabase = InMemoryBlogDatabase(postDtos, userDtos, commentDtos)

        `when`(postMapper.map(blogDatabase)).thenReturn(posts)
        `when`(remoteSource.posts()).thenReturn(postDtosSingle)
        `when`(remoteSource.users()).thenReturn(userDtosSingle)
        `when`(remoteSource.comments()).thenReturn(commentDtosSingle)

        `when`(postDatabaseEntityMapper.map(postDbEntities)).thenReturn(posts)
        `when`(localSource.mappedPosts()).thenReturn(postDbEntitiesFlowable)

        doNothing().`when`(localSource).savePosts(postDtos)
        doNothing().`when`(localSource).saveUsers(userDtos)
        doNothing().`when`(localSource).saveComments(commentDtos)

        // When
        repository.posts().subscribe()

        // Then
        verify(localSource).mappedPosts()
        verify(postDatabaseEntityMapper).map(postDbEntities)
    }

    @Test
    fun `fetching posts should invoke posts,users and comments on remotesource and save on localsource`() {
        // Arrange
        val postDbEntities = listOf(mock(PostEntity::class.java))
        val postDtos = listOf(mock(PostDto::class.java))
        val userDtos = listOf(mock(UserDto::class.java))
        val commentDtos = listOf(mock(CommentDto::class.java))

        val postDbEntitiesFlowable = Flowable.just(postDbEntities)
        val postDtosSingle = Single.just(postDtos)
        val userDtosSingle = Single.just(userDtos)
        val commentDtosSingle = Single.just(commentDtos)

        val posts = singletonList(mock(Post::class.java))
        val blogDatabase = InMemoryBlogDatabase(postDtos, userDtos, commentDtos)

        `when`(postMapper.map(blogDatabase)).thenReturn(posts)
        `when`(remoteSource.posts()).thenReturn(postDtosSingle)
        `when`(remoteSource.users()).thenReturn(userDtosSingle)
        `when`(remoteSource.comments()).thenReturn(commentDtosSingle)

        `when`(postDatabaseEntityMapper.map(postDbEntities)).thenReturn(posts)
        `when`(localSource.mappedPosts()).thenReturn(postDbEntitiesFlowable)

        doNothing().`when`(localSource).savePosts(postDtos)
        doNothing().`when`(localSource).saveUsers(userDtos)
        doNothing().`when`(localSource).saveComments(commentDtos)

        // When
        repository.posts().subscribe()

        // Then
        verify(remoteSource).posts()
        verify(remoteSource).users()
        verify(remoteSource).comments()
        verify(postMapper).map(blogDatabase)

        verify(localSource).savePosts(postDtos)
        verify(localSource).saveUsers(userDtos)
        verify(localSource).saveComments(commentDtos)
    }


    @Test
    fun `fetching comments should invoke comments on remotesource and localsource also map on commentmapper`() {
        // Arrange
        val postId = 1
        val commentDtos = rawComments(postId)
        val commentDtosSingle = Single.just(commentDtos)
        val commentDtosFlowable = Flowable.just(commentDtos)

        val comments = singletonList(mock(Comment::class.java))

        `when`(remoteSource.comments()).thenReturn(commentDtosSingle)
        `when`(localSource.comments(postId)).thenReturn(commentDtosFlowable)
        `when`(commentMapper.map(commentDtos)).thenReturn(comments)
        doNothing().`when`(localSource).saveComments(commentDtos)

        // When
        repository.comments(postId).subscribe()

        // Then
        verify(remoteSource).comments()
        verify(localSource).comments(postId)
        verify(commentMapper, times(2)).map(commentDtos)
    }

    @Test
    fun `fetching comments should invoke comments on localsource and map on commentmapper`() {
        // Arrange
        val postId = 1
        val commentDtos = listOf(mock(CommentDto::class.java))//rawComments(postId)
        val commentDtosSingle = Single.just(commentDtos)
        val commentDtosFlowable = Flowable.just(commentDtos)

        val comments = singletonList(mock(Comment::class.java))

        doNothing().`when`(localSource).saveComments(commentDtos)
        `when`(commentMapper.map(commentDtos)).thenReturn(comments)
        `when`(localSource.comments(postId)).thenReturn(commentDtosFlowable)
        `when`(remoteSource.comments()).thenReturn(commentDtosSingle)

        // When
        repository.comments(postId).subscribe()

        // Then
        verify(localSource).comments(postId)
        verify(commentMapper).map(commentDtos)
    }

    @Test
    fun `fetching comments should invoke comments on remotesource and save on localsource`() {
        // Arrange
        val postId = 1
        val commentDtos = listOf(mock(CommentDto::class.java))//rawComments(postId)
        val commentDtosSingle = Single.just(commentDtos)
        val commentDtosFlowable = Flowable.just(commentDtos)

        val comments = singletonList(mock(Comment::class.java))

        doNothing().`when`(localSource).saveComments(commentDtos)
        `when`(commentMapper.map(commentDtos)).thenReturn(comments)
        `when`(localSource.comments(postId)).thenReturn(commentDtosFlowable)
        `when`(remoteSource.comments()).thenReturn(commentDtosSingle)

        // When
        repository.comments(postId).subscribe()

        // Then
        verify(remoteSource).comments()
        verify(localSource).saveComments(commentDtos)
    }

    private fun rawComments(postId: Int): List<CommentDto> {
        return listOf(
                CommentDto(1, postId, "Shivam Singh", "shvmsngh91@gmail.com", "test comment 1"),
                CommentDto(2, postId, "Tirupati Balan", "tirupati17@gmail.com", "test comment 2"),
                CommentDto(3, postId, "Shekhar Gupta", "shekhargupat@gmail.com", "test comment 3")
        )
    }

}