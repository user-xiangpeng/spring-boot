package com.springboot.xp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.xp.bean.Result;
import com.springboot.xp.dao.mongo.mapper.MongoDao;
import com.springboot.xp.dao.mongo.model.User;

@RestController
@RequestMapping("mongo")
public class MongoUserController extends BaseController {

    @Autowired
    private MongoDao<User> userDao;

    @GetMapping("/user/add")
    public Result addUserInfo(User user) {
        userDao.insert(user);
        return Result.success();
    }

    @GetMapping("/user")
    public Result getUserInfo(@RequestParam(value = "phone", required = true) String phone) {
        return Result.success(userDao.query(Query.query(Criteria.where("phone").is(phone)), User.class));
    }

    @GetMapping("/user/list")
    public Result listUserInfo() {
        return Result.success(userDao.query(new Query(), User.class));
    }

}
