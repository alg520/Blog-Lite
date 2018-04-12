package com.shivamsingh.blog_lite.data.mapper

import com.shivamsingh.blog_lite.data.BlogDatabase
import com.shivamsingh.blog_lite.data.source.remote.dto.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class PostMapperTest {

    private lateinit var postMapper: PostMapper

    @Before
    fun setup() {
        postMapper = PostMapper()
    }

    @Test
    fun `blogdatabse with posts, users and comments dto should get mapped to domain model post`() {
        // Arrange

        // // Fields t verify
        val userId = 1
        val postId = 2
        val title = "some post title"
        val body = "some post content"
        val email = "shvmsngh91@gmail.com"

        val postDtos = listOf<PostDto>(PostDto(userId, postId, title, body))
        val userDtos = listOf<UserDto>(UserDto(userId, "shivam singh", "shvmsngh91",
                email, addressDto(), "8108758799",
                "https://github.com/shivamsingh", companyDto()))
        val commentDtos = listOf<CommentDto>(CommentDto(1, postId, "tirupati balan",
                "tirupati17@gmail.com", "some test comment"))
        val blogDatabse = BlogDatabase(postDtos, userDtos, commentDtos)

        // When
        val posts = postMapper.map(blogDatabse)

        // Then
        assertThat(posts.size).isEqualTo(postDtos.size)
        assertThat(posts[0].id).isEqualTo(postId)
        assertThat(posts[0].title).isEqualTo(title)
        assertThat(posts[0].body).isEqualTo(body)
        assertThat(posts[0].email).isEqualTo(email)
        assertThat(posts[0].commentsCount).isEqualTo(commentDtos.size)
    }

    @Test
    fun `email should be blank when no user dto exist for post dto`() {
        // Arrange

        // // Fields t verify
        val userId = 1
        val postId = 2
        val title = "some post title"
        val body = "some post content"
        val email = "shvmsngh91@gmail.com"

        val postDtos = listOf<PostDto>(PostDto(3, postId, title, body))
        val userDtos = listOf<UserDto>(UserDto(userId, "shivam singh", "shvmsngh91",
                email, addressDto(), "8108758799",
                "https://github.com/shivamsingh", companyDto()))
        val commentDtos = listOf<CommentDto>(CommentDto(1, postId, "tirupati balan",
                "tirupati17@gmail.com", "some test comment"))
        val blogDatabse = BlogDatabase(postDtos, userDtos, commentDtos)

        // When
        val posts = postMapper.map(blogDatabse)

        // Then
        assertThat(posts.size).isEqualTo(postDtos.size)
        assertThat(posts[0].id).isEqualTo(postId)
        assertThat(posts[0].title).isEqualTo(title)
        assertThat(posts[0].body).isEqualTo(body)
        assertThat(posts[0].email).isEqualTo("")
        assertThat(posts[0].commentsCount).isEqualTo(commentDtos.size)
    }

    @Test
    fun `comment count should be zero when no comment dto exist for post dto`() {
        // Arrange

        // // Fields t verify
        val userId = 1
        val postId = 2
        val title = "some post title"
        val body = "some post content"
        val email = "shvmsngh91@gmail.com"

        val postDtos = listOf<PostDto>(PostDto(userId, postId, title, body))
        val userDtos = listOf<UserDto>(UserDto(userId, "shivam singh", "shvmsngh91",
                email, addressDto(), "8108758799",
                "https://github.com/shivamsingh", companyDto()))
        val commentDtos = listOf<CommentDto>(CommentDto(1, 1, "tirupati balan",
                "tirupati17@gmail.com", "some test comment"))
        val blogDatabse = BlogDatabase(postDtos, userDtos, commentDtos)

        // When
        val posts = postMapper.map(blogDatabse)

        // Then
        assertThat(posts.size).isEqualTo(postDtos.size)
        assertThat(posts[0].id).isEqualTo(postId)
        assertThat(posts[0].title).isEqualTo(title)
        assertThat(posts[0].body).isEqualTo(body)
        assertThat(posts[0].email).isEqualTo(email)
        assertThat(posts[0].commentsCount).isEqualTo(0)
    }

    // dummy address
    private fun addressDto(): AddressDto {
        return AddressDto("", "", "", "", GeoDto("", ""))
    }

    // dummy company
    private fun companyDto(): CompanyDto {
        return CompanyDto("", "", "")
    }
}