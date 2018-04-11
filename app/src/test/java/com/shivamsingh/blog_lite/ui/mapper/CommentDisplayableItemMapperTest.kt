package com.shivamsingh.blog_lite.ui.mapper

import com.aasaanjobs.partnerinternal.recyclerview.DisplayableItem.Companion.toDisplayableItem
import com.nhaarman.mockito_kotlin.verify
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.ui.features.COMMENT_ITEM
import com.shivamsingh.blog_lite.ui.model.CommentEntity
import com.shivamsingh.blog_lite.util.BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CommentDisplayableItemMapperTest : BaseTest() {

    @Mock
    private lateinit var commentMapper: CommentMapper

    private lateinit var commentDisplayableItemMapper: CommentDisplayableItemMapper

    @Before
    fun setup() {
        commentDisplayableItemMapper = CommentDisplayableItemMapper(commentMapper)
    }

    @Test
    fun `comment should get mapped to comment entity`() {
        // Arrange
        val comment = mock(Comment::class.java)
        val commentEntity = mock(CommentEntity::class.java)
        `when`(commentMapper.map(comment)).thenReturn(commentEntity)

        // When
        commentDisplayableItemMapper.map(comment)

        // Then
        verify(commentMapper).map(comment)
    }

    @Test
    fun `comment entity should get wrapped in displayable item`() {
        // Arrange
        val comment = mock(Comment::class.java)
        val commentEntity = mock(CommentEntity::class.java)
        `when`(commentMapper.map(comment)).thenReturn(commentEntity)

        // When
        val displayableItem = commentDisplayableItemMapper.map(comment)
        val expectedDisplayableItem = toDisplayableItem(commentEntity, COMMENT_ITEM)

        // Then
        assertThat(displayableItem).isEqualTo(expectedDisplayableItem)
    }
}