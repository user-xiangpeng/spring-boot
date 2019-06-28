package com.springboot.xp.dao.mongo.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MongoDao<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public T insert(T t) {
        if (null == t) {
            return null;
        }
        mongoTemplate.insert(t);
        return t;
    }

    public List<T> query(Query query, Class<T> c) {
        return mongoTemplate.find(query, c);
    }
}
