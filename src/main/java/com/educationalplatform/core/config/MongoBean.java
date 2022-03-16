package com.educationalplatform.core.config;

import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.stereotype.Component;

@Component
public class MongoBean {

    private final MongoDatabaseFactory mongo;

    public MongoBean(MongoDatabaseFactory mongo) {
        this.mongo = mongo;
    }

}
