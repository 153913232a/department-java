package com.example.demo.service;


import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired()
    private UserMapper userMapper;

    public List<User> getUsers(Map<String, Object> map) {
        return userMapper.getUsers(map);
    }

    public void insert(Map<String, Object> user) {
        userMapper.insert(user);
    }

    public void update(Map<String, Object> user) {
        userMapper.update(user);
    }

    public void delete(int userId) {
        userMapper.delete(userId);
    }
}
