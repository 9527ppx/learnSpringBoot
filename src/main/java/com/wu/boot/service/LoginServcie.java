package com.wu.boot.service;

import com.wu.boot.pojo.ResponseResult;
import com.wu.boot.pojo.User;

public interface LoginServcie {
    ResponseResult login(User user);

    ResponseResult logout();
}
