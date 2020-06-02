package com.lvlin.location_system.service;

import com.lvlin.location_system.entity.User;
import com.lvlin.location_system.template.Result;

import java.util.List;

public interface UserService {
    Result login(User user);
    Result register(User user);
    Result deleteByName(String username);
    Result changePassword(String username,String password);
    List<User> getAll();
}
