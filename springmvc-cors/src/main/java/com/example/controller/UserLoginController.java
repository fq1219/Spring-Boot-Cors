package com.example.controller;

import com.example.data.User;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static com.example.data.UserDB.userdb;

@RestController
@RequestMapping("/user/login")
public class UserLoginController {

    @RequestMapping(method = RequestMethod.GET)
    Object getInfo(HttpSession session) {
        Object object = session.getAttribute("loginer");
        return object == null ? Lists.newArrayList("未登录") : object;
    }

    @RequestMapping(method = RequestMethod.POST)
    List<String> login(HttpSession session, @RequestBody String name) {
        Optional<User> user = userdb.asMap().values().stream().filter(user1 -> user1.getName().equals(name)).findAny();
        if (user.isPresent()) {
            session.setAttribute("loginer", user.get());
            return Lists.newArrayList("登录成功!");
        }
        return Lists.newArrayList("登录失败, 找不到用户名:" + name);
    }
}
