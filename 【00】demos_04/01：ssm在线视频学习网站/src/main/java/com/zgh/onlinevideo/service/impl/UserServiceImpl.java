package com.zgh.onlinevideo.service.impl;

import com.zgh.onlinevideo.dao.UserDao;
import com.zgh.onlinevideo.domain.User;
import com.zgh.onlinevideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User login(User user) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("email", user.getEmail());
        map.put("password", user.getPassword());

        return userDao.findUserByCondition(map);
    }

    @Override
    public int regist(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public User existEmail(String email) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("email", email);

        return userDao.findUserByCondition(map);
    }
}
