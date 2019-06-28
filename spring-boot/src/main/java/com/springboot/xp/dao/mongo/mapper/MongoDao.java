package com.springboot.xp.dao.mongo.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;

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

    public WriteResult updateById(Long id, T t) {
        Update update = new Update();
        Class<?> tc = t.getClass();
        Field[] declaredFields = tc.getDeclaredFields();
        Stream.of(declaredFields).forEach(i -> {
            update.set(i.getName(), getValueByField(t, i));
        });
        return mongoTemplate.upsert(Query.query(Criteria.where("id").is(id)), update, t.getClass());
    }

    private Object getValueByField(T t, Field field) {
        try {
            Method method = t.getClass().getMethod("get" + getMethodName(field.getName()));
            return method.invoke(t);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getMethodName(String fildeName) {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    public WriteResult deleteById(Long id, Class<T> c) {
        return mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), c);
    }
}
