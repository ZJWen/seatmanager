package com.zjw.seatmanager.service;

import com.zjw.seatmanager.entity.User;

public interface UserService {
    public User findUserByName(String username);
}
