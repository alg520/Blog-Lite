package com.shivamsingh.blog_lite.data.repository

import com.shivamsingh.blog_lite.data.InMemoryBlogDatabase
import com.shivamsingh.blog_lite.data.mapper.CommentMapper
import com.shivamsingh.blog_lite.data.mapper.PostMapper
import com.shivamsingh.blog_lite.data.source.BlogRemoteSource
import com.shivamsingh.blog_lite.data.source.local.BlogLocalSource
import com.shivamsingh.blog_lite.domain.model.Comment
import com.shivamsingh.blog_lite.domain.model.Post
import com.shivamsingh.blog_lite.domain.repository.BlogRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function3

class BlogRepositoryImpl constructor(private val localSource: BlogLocalSource,
                                     private val remoteSource: BlogRemoteSource,
                                     private val postMapper: PostMapper,
                                     private val commentMapper: CommentMapper) : BlogRepository {

    override fun posts(): Flowable<List<Post>> {
        return Flowable.ambArray(
                localSource.mappedPosts(),
                blogDatabase().map { postMapper.map(it) }.toFlowable()
        )
    }

    override fun comments(postId: Int): Flowable<List<Comment>> {
        return Flowable.ambArray(
                localSource.comments(postId).map { commentMapper.map(it) },
                remoteSource.comments()
                        .doOnSuccess { localSource.saveComments(it) }
                        .toObservable()
                        .flatMap { Observable.fromIterable(it) }
                        .filter { it.postId == postId }
                        .toList()
                        .map { commentMapper.map(it) }
                        .toFlowable()
        )
    }

    private fun blogDatabase(): Single<InMemoryBlogDatabase> {
        return Single.zip(
                remoteSource
                        .posts()
                        .doOnSuccess { localSource.savePosts(it) },
                remoteSource
                        .users()
                        .doOnSuccess { localSource.saveUsers(it) },
                remoteSource
                        .comments()
                        .doOnSuccess { localSource.saveComments(it) },
                Function3(::InMemoryBlogDatabase))
    }
}