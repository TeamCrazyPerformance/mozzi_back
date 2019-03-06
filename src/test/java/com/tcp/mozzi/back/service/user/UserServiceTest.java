package com.tcp.mozzi.back.service.user;

import com.tcp.mozzi.back.domain.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserServiceTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Before
    public void addUser() {
        User user = User.builder()
                .name("김현욱")
                .password("testpassword")
                .status(User.UserStatus.OK)
                .nickname("KUvH")
                .studentNum("16100000")
                .phoneNum("01012345678")
                .email("kuvh@live.co.kr")
                .birthday(new Date())
                .build();
        userService.addUser(user);
    }

    @Test
    public void getFirstUser() {
        List<User> users = userService.getUsers();

        User user = userService.getUserById(users.get(0).getId());
        log.info("First User : {}", user);
    }

    @Test
    public void getUsers() {
        List<User> users = userService.getUsers();
        log.info("Users : {}", users);
    }

    @Test
    public void updateFirstUser() {
        List<User> users = userService.getUsers();

        User user = userService.getUserById(users.get(0).getId());
        user.setPhoneNum("01098765432");
        userService.updateUser(user);

        user = userService.getUserById(users.get(0).getId());
        log.info("First User : {}", user);
    }

    @After
    public void deleteFirstUser() {
        List<User> users = userService.getUsers();

        userService.deleteUserById(users.get(0).getId());
    }

}
