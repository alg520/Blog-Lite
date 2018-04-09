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
import io.reactivex.functions.BiFunction

class BlogRepositoryImpl constructor(private val remoteSource: BlogRemoteSource,
                                     private val postMapper: PostMapper,
                                     private val commentMapper: CommentMapper) : BlogRepository {

    override fun posts(): Single<List<Post>> {

        return blogDatabse()
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

    private fun blogDatabse(): Single<BlogDatabase> {
        return Single.just(BlogDatabase())
                .zipWith(remoteSource.posts(), BiFunction<BlogDatabase, List<PostDto>, BlogDatabase> { t1, t2 -> t1.also { t1.posts = t2 } })
                .zipWith(remoteSource.users(), BiFunction<BlogDatabase, List<UserDto>, BlogDatabase> { t1, t2 -> t1.also { t1.users = t2 } })
                .zipWith(remoteSource.comments(), BiFunction<BlogDatabase, List<CommentDto>, BlogDatabase> { t1, t2 -> t1.also { t1.comments = t2 } })
    }
}