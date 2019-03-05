package com.tcp.mozzi.back.service.user;

import com.tcp.mozzi.back.domain.user.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    List<User> getUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUserById(Integer id);
}
