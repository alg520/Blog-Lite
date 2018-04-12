package com.shivamsingh.blog_lite.ui.mapper

import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.util.BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class CommentMapperTest : BaseTest() {

    val avatarUrlWithEmailPlaceHolder = "http://testdomain/200/%s.png"
    private lateinit var commentMapper: CommentMapper

    @Before
    fun setup() {
        commentMapper = CommentMapper(avatarUrlWithEmailPlaceHolder)
    }

    @Test
    fun `comment should get mapped to comment entity with avatar`() {
        // Arrange
        val id = 1
        val postId = 1
        val name = "shivam"
        val email = "shvmsngh91@gmail.com"
        val body = "some test comment"

        val comment = Comment(id, postId, name, email, body)

        // When
        val commentEntity = commentMapper.map(comment)

        // Then

        assertThat(commentEntity.id).isEqualTo(id)
        assertThat(commentEntity.postId).isEqualTo(postId)
        assertThat(commentEntity.name).isEqualTo(name)
        assertThat(commentEntity.email).isEqualTo(email)
        assertThat(commentEntity.body).isEqualTo(body)
        assertThat(commentEntity.avatar).isEqualTo(avatarUrlWithEmailPlaceHolder.format(email))
    }
}