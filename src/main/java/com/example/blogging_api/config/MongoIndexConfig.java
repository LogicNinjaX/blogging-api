package com.example.blogging_api.config;

import com.example.blogging_api.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
public class MongoIndexConfig {

    private final MongoTemplate mongoTemplate;

    public MongoIndexConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void initIndexes() {
        mongoTemplate.indexOps(User.class).ensureIndex(new Index().on("username", org.springframework.data.domain.Sort.Direction.ASC).unique());
        mongoTemplate.indexOps(User.class).ensureIndex(new Index().on("email", org.springframework.data.domain.Sort.Direction.ASC).unique());
    }
}
