package com.example.blogging_api.service.impl;

import com.example.blogging_api.dto.BlogDto;
import com.example.blogging_api.dto.UpdateBlogDto;
import com.example.blogging_api.entity.Blog;
import com.example.blogging_api.exception.PostNotFoundException;
import com.example.blogging_api.repository.BlogRepository;
import com.example.blogging_api.service.BlogService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BlogServiceImpl implements BlogService {

    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    private BlogRepository blogRepository;
    private ModelMapper modelMapper;

    public BlogServiceImpl(BlogRepository blogRepository, ModelMapper modelMapper) {
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
    }

    public BlogDto saveBlog(Blog blog) {
        BlogDto blogDto =  modelMapper.map(blogRepository.save(blog), BlogDto.class);
        logger.info("post created with title: {}", blog.getTitle());

        return blogDto;
    }

    @Override
    public List<BlogDto> findByAuthor(Pageable pageable, String username) {
        return blogRepository.findByAuthor(pageable, username)
                .stream().map(blog -> modelMapper.map(blog, BlogDto.class)).toList();
    }

    @Override
    public BlogDto findBlog(ObjectId postId) {
        Blog blog = blogRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("post not found"));
        logger.info("post found");
        return modelMapper.map(blog, BlogDto.class);
    }

    @Override
    public void deleteBlog(ObjectId postId) {
        blogRepository.deleteById(postId);
        logger.info("post deleted");
    }

    @Override
    public BlogDto updateBlog(ObjectId postId, UpdateBlogDto updateBlogDto) {

        Blog tempBlog = blogRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("post not found"));

        if (Objects.nonNull(updateBlogDto.getTitle())){
            tempBlog.setTitle(updateBlogDto.getTitle());
        }

        if (Objects.nonNull(updateBlogDto.getContent())){
            tempBlog.setContent(updateBlogDto.getContent());
        }

        logger.info("post details updated");

        return modelMapper.map(blogRepository.save(tempBlog), BlogDto.class);
    }
}
