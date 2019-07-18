package com.springboot.xp.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
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
import com.springboot.xp.dao.mysql.model.GwAccount;
import com.springboot.xp.service.IGwAccountService;

@RestController
@RequestMapping("mongo")
public class MongoUserController extends BaseController {

    @Autowired
    private MongoDao<User> userDao;
    @Autowired
    private MongoDao<GwAccount> gwDao;
    @Autowired
    private IGwAccountService gwAccountService;

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

    @GetMapping("/user/update")
    public Result updateUserInfo(@RequestParam(value = "id", required = true) Long id, User user) {
        return Result.success(userDao.updateById(id, user));
    }

    @GetMapping("/user/delete")
    public Result deleteById(@RequestParam(value = "id", required = true) Long id) {
        return Result.success(userDao.deleteById(id, User.class));
    }

    @GetMapping("/gw/copy")
    public Result copyGwAccountToMongo() {
        int i = 0;
        while (true) {
            List<GwAccount> gwAccounts = gwAccountService.listByLimit(i, 1000);
            System.out.println("start insert  time is " + DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
            gwDao.insert(gwAccounts, GwAccount.class);
            System.out.println("end insert time is " + DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
            if (gwAccounts.size() < 1000) {
                break;
            }
            i += 1000;
        }
        return Result.success("copy is ok");
    }

    @GetMapping("/gw/list")
    public Result listGwAccount(int size) {
        return Result.success(gwAccountService.listByLimit(0, size));
    }

}
