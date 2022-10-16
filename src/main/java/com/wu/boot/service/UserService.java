package com.wu.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wu.boot.mapper.UserMapper;
import com.wu.boot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface UserService extends IService<User> {

}
