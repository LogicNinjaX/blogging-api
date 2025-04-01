package com.example.blogging_api.controller;


import com.example.blogging_api.dto.ApiResponse;
import com.example.blogging_api.dto.BlogDto;
import com.example.blogging_api.dto.CreateBlogDto;
import com.example.blogging_api.dto.UpdateBlogDto;
import com.example.blogging_api.entity.Blog;
import com.example.blogging_api.service.BlogService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class BlogController {

    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @PostMapping("/blog/posts")
    public ResponseEntity<ApiResponse<BlogDto>> savePost(@RequestBody CreateBlogDto createBlogDto,
                                                         @AuthenticationPrincipal UserDetails userDetails) {

        BlogDto blogDto = blogService.saveBlog(new Blog(
                        createBlogDto.getTitle(),
                        createBlogDto.getContent(),
                        userDetails.getUsername()
                )
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "post created", blogDto));
    }

    @GetMapping("/blog/posts")
    public ResponseEntity<ApiResponse<List<BlogDto>>> getAllPosts(@RequestParam("page-number") int pageNumber,
                                                                  @RequestParam("page-size") int pageSize,
                                                                  @AuthenticationPrincipal UserDetails userDetails) {

        List<BlogDto> blogList = blogService.findByAuthor(PageRequest.of(pageNumber - 1, pageSize), userDetails.getUsername());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, "request successful", blogList));
    }

    @GetMapping("/blog/posts/{post-id}")
    public ResponseEntity<ApiResponse<BlogDto>> getAllPosts(@PathVariable("post-id") ObjectId postId,
                                                            @AuthenticationPrincipal UserDetails userDetails) {

        BlogDto blogDto = blogService.findBlog(postId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, "post found", blogDto));
    }


    @DeleteMapping("/blog/posts/{post-id}")
    public ResponseEntity<ApiResponse<ApiResponse<?>>> deletePost(@PathVariable("post-id") ObjectId postId) {
        blogService.deleteBlog(postId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, "post deleted", null));
    }


    @PutMapping("/blog/posts/{post-id}")
    public ResponseEntity<ApiResponse<BlogDto>> updatePost(@PathVariable("post-id") ObjectId postId, @RequestBody UpdateBlogDto updateBlogDto){
        BlogDto blogDto = blogService.updateBlog(postId, updateBlogDto);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ApiResponse<>(true, "post updated", blogDto));
    }
}
