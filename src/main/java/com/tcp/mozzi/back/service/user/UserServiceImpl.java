package com.tcp.mozzi.back.service.user;

import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.dto.user.UpdateUserPasswordRequestDto;
import com.tcp.mozzi.back.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.StringTokenizer;

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
        return userMapper.selectUserByUserId(id);
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
        userMapper.updateUser(user);
    }

    @Override
    public void updateUserPassword(User user, String newPassword) {
        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        userMapper.updateUserPassword(user.getUserId(), encryptedPassword);
    }

    @Override
    public boolean isValidUser(User user, String curPassword) { return userMapper.isValidUser(user.getUserId(), curPassword); }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public boolean isExistUserByName(String name) {
        return userMapper.isExistUserByName(name);
    }

    @Override
    public User userDetailById(Integer id, String role) {
        final String closed = "PRIVATE";
        User user = userMapper.selectUserByUserId(id);
        if(role.equals(user.getRole().toString())){
            return user;
        }
        StringTokenizer allow = new StringTokenizer(user.getAllow(), ",");
        String temp;
        while(allow.hasMoreTokens()){
            temp = allow.nextToken().trim();
            switch (temp){
                case "studentNumber":
                    user.setStudentNum(closed);
                    continue;
                case "phoneNumber":
                    user.setPhoneNum(closed);
                    continue;
                case "email":
                    user.setEmail(closed);
                    continue;
                case "birthday":
                    user.setBirthday(null);
                    continue;
            }
        }

        return user;
    }
}
