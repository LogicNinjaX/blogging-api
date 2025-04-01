package com.example.blogging_api.service;

import com.example.blogging_api.dto.BlogDto;
import com.example.blogging_api.dto.UpdateBlogDto;
import com.example.blogging_api.entity.Blog;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    BlogDto saveBlog(Blog blog);

    List<BlogDto> findByAuthor(Pageable pageable, String username);

    BlogDto findBlog(ObjectId postId);

    void deleteBlog(ObjectId postId);

    BlogDto updateBlog(ObjectId postId, UpdateBlogDto updateBlogDto);
}
