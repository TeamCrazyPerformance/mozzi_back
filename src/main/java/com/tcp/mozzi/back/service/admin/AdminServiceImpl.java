package com.tcp.mozzi.back.service.admin;

import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.mapper.admin.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) { this.adminMapper = adminMapper; }

    @Override
    public List<User> getWaitUsers(int page, int limit) {
        return adminMapper.selectWaitUser(page, limit);
    }

    @Override
    public List<User> getAllUsers(int page, int limit) {
        return adminMapper.selectAllUser(page, limit);
    }

    @Override
    public int totalWaitUser() {
        return adminMapper.getTotalWaitUser();
    }

    @Override
    public int totalUser() {
        return adminMapper.getTotalUser();
    }
}
