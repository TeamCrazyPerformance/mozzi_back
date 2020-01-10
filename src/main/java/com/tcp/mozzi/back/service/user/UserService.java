package com.tcp.mozzi.back.service.user;

import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.dto.user.UpdateUserPasswordRequestDto;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    List<User> getUsers();
    void addUser(User user);
    void updateUser(User user);
    void updateUserPassword(User user, String newPassword);
    boolean isValidUser(User user, String curPassword);
    void deleteUserById(Integer id);
    boolean isExistUserByName(String name);
    User userDetailById(Integer id, String role);
}
