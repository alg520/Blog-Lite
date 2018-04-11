package com.shivamsingh.blog_lite.data.mapper

import com.shivamsingh.blog_lite.data.mapper.base.Mapper
import com.shivamsingh.blog_lite.data.source.remote.dto.CommentDto
import com.shivamsingh.blog_lite.domain.model.Comment
import javax.inject.Inject

class CommentMapper @Inject constructor() : Mapper<Comment, CommentDto>() {

    override fun map(commentDto: CommentDto): Comment = Comment(commentDto.id, commentDto.postId, commentDto.name, commentDto.email, commentDto.body)
}