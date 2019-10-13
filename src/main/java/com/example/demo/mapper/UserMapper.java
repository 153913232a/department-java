package com.example.demo.mapper;


import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper {

    List<User> getUsers(Map<String, Object> map);

    void insert(Map<String, Object> user);

    void update(Map<String, Object> user);

    void delete(int userId);
}
