package com.lvlin.location_system.dao;

import com.lvlin.location_system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  UserDao {
    User selectByName(String username);
    void insert(User user);
    void deleteByName(String username);
    void updateByName(String username,String password);
    List<User> selectAll();
}
