package com.shivamsingh.blog_lite.data.mapper

import com.shivamsingh.blog_lite.data.source.remote.dto.CommentDto
import com.shivamsingh.blog_lite.util.BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class CommentMapperTest : BaseTest() {

    private lateinit var commentMapper: CommentMapper

    @Before
    fun setup() {
        commentMapper = CommentMapper()
    }

    @Test
    fun `comment dto from should get mapped to comment`() {
        // Arrange
        val id = 1
        val postId = 1
        val name = "Shivam Singh"
        val email = "shvmsngh91@gmail.com"
        val body = "some test comment"
        val commentDto = CommentDto(id, postId, name, email, body)

        // When
        val comment = commentMapper.map(commentDto)

        // Then
        assertThat(comment.id).isEqualTo(id)
        assertThat(comment.postId).isEqualTo(postId)
        assertThat(comment.name).isEqualTo(name)
        assertThat(comment.email).isEqualTo(email)
        assertThat(comment.body).isEqualTo(body)
    }
}