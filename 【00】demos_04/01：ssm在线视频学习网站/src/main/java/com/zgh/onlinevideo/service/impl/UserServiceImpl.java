package com.zgh.onlinevideo.service.impl;

import com.zgh.onlinevideo.dao.UserDao;
import com.zgh.onlinevideo.domain.User;
import com.zgh.onlinevideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public int regist(User user) {
        return userDao.insertUser(user);
    }
}
