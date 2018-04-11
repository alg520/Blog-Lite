package com.shivamsingh.blog_lite.ui.mapper

import com.nhaarman.mockito_kotlin.verify
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.ui.model.PostEntity
import com.shivamsingh.blog_lite.util.BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class PostDisplayableItemMapperTest : BaseTest() {

    @Mock
    private lateinit var postMapper: PostMapper

    private lateinit var postDisplayableItemMapper: PostDisplayableItemMapper

    @Before
    fun setup() {
        postDisplayableItemMapper = PostDisplayableItemMapper(postMapper)
    }

    @Test
    fun `post should get mapped to post entity`() {
        // Arrange
        val post = mock(Post::class.java)
        val postEntity = mock(PostEntity::class.java)
        `when`(postMapper.map(post)).thenReturn(postEntity)

        // When
        postDisplayableItemMapper.map(post)

        // Then
        verify(postMapper).map(post)
    }

    @Test
    fun `post entity should get wrapped in displayable item`() {
        // Arrange
        val post = mock(Post::class.java)
        val postEntity = mock(PostEntity::class.java)
        `when`(postMapper.map(post)).thenReturn(postEntity)

        // When
        val displayablePost = postDisplayableItemMapper.map(post)
        val expectedDisplayablePost = postDisplayableItemMapper.map(post)

        // Then
        assertThat(displayablePost).isEqualTo(expectedDisplayablePost)
    }

}