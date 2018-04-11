package com.example.controller;

import com.example.data.User;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.example.data.UserDB.userdb;

@RestController
@RequestMapping("/users")
public class UserController {

    @CrossOrigin(origins = "http://localhost:49290")
    @RequestMapping(method = RequestMethod.GET)
    List<User> getList() {
        return Lists.newArrayList(userdb.asMap().values());
    }

    @CrossOrigin(origins = "http://localhost:49291")
    @RequestMapping(method = RequestMethod.POST)
    List<String> add(@RequestBody String name) {
        if (userdb.asMap().values().stream().anyMatch(user -> user.getName().equals(name))) {
            return Lists.newArrayList("添加失败, 用户名'" + name + "'已存在");
        }
        String id = UUID.randomUUID().toString();
        userdb.put(id, new User(id, name));
        return Lists.newArrayList("添加成功: " + userdb.getIfPresent(id));
    }
}
