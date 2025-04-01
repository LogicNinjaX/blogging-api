package com.example.blogging_api.repository;

import com.example.blogging_api.entity.Blog;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BlogRepository extends MongoRepository<Blog, ObjectId> {

    List<Blog> findByAuthor(Pageable pageable, String author);
}
