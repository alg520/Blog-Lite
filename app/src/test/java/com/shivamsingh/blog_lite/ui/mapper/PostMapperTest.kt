package com.shivamsingh.blog_lite.ui.mapper

import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.util.BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class PostMapperTest : BaseTest() {

    val avatarUrlWithEmailPlaceHolder = "http://testdomain/200/%s.png"
    private lateinit var postMapper: PostMapper

    @Before
    fun setup() {
        postMapper = PostMapper(avatarUrlWithEmailPlaceHolder)
    }

    @Test
    fun `post should get mapped to post entity`() {
        // Arrange
        val id = 1
        val title = "test title"
        val body = "test post content"
        val email = "shvmsngh91@gmail.com"
        val commentsCount = 5

        val post = Post(id, title, body, email, commentsCount)

        // When

        val postEntity = postMapper.map(post)

        // Then

        assertThat(postEntity.id).isEqualTo(id)
        assertThat(postEntity.title).isEqualTo(title)
        assertThat(postEntity.body).isEqualTo(body)
        assertThat(postEntity.email).isEqualTo(email)
        assertThat(postEntity.commentsCount).isEqualTo(commentsCount)
        assertThat(postEntity.avatar).isEqualTo(avatarUrlWithEmailPlaceHolder.format(email))
    }

}