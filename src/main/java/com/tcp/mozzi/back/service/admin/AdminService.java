package com.tcp.mozzi.back.service.admin;

import com.tcp.mozzi.back.domain.user.User;

import java.util.List;

public interface AdminService {
    List<User> getWaitUsers(int page, int limit);
    int totalWaitUsers();
}
