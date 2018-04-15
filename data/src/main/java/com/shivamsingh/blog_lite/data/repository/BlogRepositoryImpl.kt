package com.shivamsingh.blog_lite.data.repository

import com.shivamsingh.blog_lite.data.InMemoryBlogDatabase
import com.shivamsingh.blog_lite.data.mapper.CommentMapper
import com.shivamsingh.blog_lite.data.mapper.PostDatabaseEntityMapper
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
                                     private val postDatabaseEntityMapper: PostDatabaseEntityMapper,
                                     private val postRemoteMapper: PostMapper,
                                     private val commentMapper: CommentMapper) : BlogRepository {

    override fun posts(): Flowable<List<Post>> {
        return Flowable.merge(
                localSource.mappedPosts().map { postDatabaseEntityMapper.map(it) },
                blogDatabase().map(postRemoteMapper::map).toFlowable()
        )
    }

    override fun comments(postId: Int): Flowable<List<Comment>> {
        return Flowable.merge(
                localSource.comments(postId).map(commentMapper::map),
                remoteSource.comments()
                        .doOnSuccess(localSource::saveComments)
                        .toObservable()
                        .flatMap { Observable.fromIterable(it) }
                        .filter { it.postId == postId }
                        .toList()
                        .map(commentMapper::map)
                        .toFlowable()
        )
    }

    private fun blogDatabase(): Single<InMemoryBlogDatabase> {
        return Single.zip(
                remoteSource
                        .posts()
                        .doOnSuccess(localSource::savePosts),
                remoteSource
                        .users()
                        .doOnSuccess(localSource::saveUsers),
                remoteSource
                        .comments()
                        .doOnSuccess(localSource::saveComments),
                Function3(::InMemoryBlogDatabase))
    }
}