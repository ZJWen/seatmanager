package com.zjw.seatmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjw.seatmanager.entity.User;
import com.zjw.seatmanager.mapper.UserMapper;
import com.zjw.seatmanager.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Override
    public User findUserByName(String username){
       User user= userMapper.selectOne(new QueryWrapper<User>()
        .eq("username",username)
        );

       return user;
    }

}
