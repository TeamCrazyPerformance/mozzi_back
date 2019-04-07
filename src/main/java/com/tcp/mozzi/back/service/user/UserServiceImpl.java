package com.tcp.mozzi.back.service.user;

import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectUsers();
    }

    @Override
    public void addUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public boolean isExistUserByName(String name) {
        return userMapper.isExistUserByName(name);
    }
}
