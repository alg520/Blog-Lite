package com.shivamsingh.blog_lite.data.mapper

import com.shivamsingh.blog_lite.data.source.local.entity.PostEntity
import com.shivamsingh.blog_lite.util.BaseTest
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

class PostDatabaseEntityMapperTest : BaseTest() {

    private lateinit var postDatabaseEntityMapper: PostDatabaseEntityMapper

    @Before
    fun setUp() {
        postDatabaseEntityMapper = PostDatabaseEntityMapper()
    }

    @Test
    fun `post entity from database should get mapped to domain post`() {
        // Arrange
        val id = 1
        val email = "shvmsngh91@gmail.com"
        val title = "some post title"
        val body = "some post content"
        val commentsCount = 5
        val commentDto = PostEntity(id, title, body, email, commentsCount)

        // When
        val post = postDatabaseEntityMapper.map(commentDto)

        // Then
        Assertions.assertThat(post.id).isEqualTo(id)
        Assertions.assertThat(post.title).isEqualTo(title)
        Assertions.assertThat(post.body).isEqualTo(body)
        Assertions.assertThat(post.email).isEqualTo(email)
        Assertions.assertThat(post.commentsCount).isEqualTo(commentsCount)
    }

}