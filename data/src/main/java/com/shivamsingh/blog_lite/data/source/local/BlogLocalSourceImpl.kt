package com.shivamsingh.blog_lite.data.source.local

import com.shivamsingh.blog_lite.data.source.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.dto.PostDto
import com.shivamsingh.blog_lite.data.source.dto.UserDto
import javax.inject.Inject

class BlogLocalSourceImpl @Inject constructor(val blogDatabase: BlogDatabase) : BlogLocalSource {

    override fun posts() = blogDatabase.postDao().posts()

    override fun mappedPosts() = blogDatabase.postDao().mappedPosts()

    override fun users() = blogDatabase.userDao().users()

    override fun comments() = blogDatabase.commentDao().comments()

    override fun comments(postId: Int) = blogDatabase.commentDao().comments(postId)

    override fun savePosts(posts: List<PostDto>) = blogDatabase.postDao().insertPosts(posts)

    override fun saveUsers(users: List<UserDto>) = blogDatabase.userDao().insertUsers(users)

    override fun saveComments(comments: List<CommentDto>) = blogDatabase.commentDao().insertComments(comments)
}