package com.shivamsingh.blog_lite.data.repository

import com.shivamsingh.blog_lite.data.BlogDatabase
import com.shivamsingh.blog_lite.data.mapper.CommentMapper
import com.shivamsingh.blog_lite.data.mapper.PostMapper
import com.shivamsingh.blog_lite.data.source.BlogRemoteSource
import com.shivamsingh.blog_lite.data.source.remote.dto.CommentDto
import com.shivamsingh.blog_lite.data.source.remote.dto.PostDto
import com.shivamsingh.blog_lite.data.source.remote.dto.UserDto
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.domain.repository.BlogRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function3

class BlogRepositoryImpl constructor(private val remoteSource: BlogRemoteSource,
                                     private val postMapper: PostMapper,
                                     private val commentMapper: CommentMapper) : BlogRepository {

    override fun posts(): Single<List<Post>> {
        return blogDatabase()
                .map { postMapper.map(it) }
    }

    override fun comments(postId: Int): Single<List<Comment>> {
        return remoteSource.comments()
                .toObservable()
                .flatMap { Observable.fromIterable(it) }
                .filter { it.postId == postId }
                .toList()
                .map { commentMapper.map(it) }

    }

    private fun blogDatabase(): Single<BlogDatabase> {
        return Single.zip(
                remoteSource.posts(),
                remoteSource.users(),
                remoteSource.comments(),
                createBlogDatabase())
    }

    private fun createBlogDatabase(): Function3<List<PostDto>, List<UserDto>, List<CommentDto>, BlogDatabase> {
        return Function3<List<PostDto>, List<UserDto>, List<CommentDto>, BlogDatabase> { posts, users, comments ->
            BlogDatabase(posts, users, comments)
        }
    }
}