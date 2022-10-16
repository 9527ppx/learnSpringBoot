package com.wu.boot.controller;

import com.wu.boot.pojo.ResponseResult;
import com.wu.boot.pojo.User;
import com.wu.boot.service.LoginServcie;
import com.wu.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserService userService;
//    @GetMapping("/user/{host}")
//    public List<User> getUserByHost(@PathVariable("host") String host){
//        return userService.getUserByHost(host);
//    }
    @Autowired
    private LoginServcie loginServcie;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userService.getById(id);
    }

    @RequestMapping("/hello")
    public String handle(){
        return "Hello Word";
    }
    @GetMapping("/sql")
    public String query(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from mysql.user", Long.class);
        String s = aLong.toString();
        return s;
    }

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginServcie.login(user);
    }

    @RequestMapping
    public ResponseResult logout(){
        return loginServcie.logout();
    }

}
