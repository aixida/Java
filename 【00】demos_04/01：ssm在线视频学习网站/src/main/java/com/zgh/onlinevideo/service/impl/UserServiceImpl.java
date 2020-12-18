package com.zgh.onlinevideo.service.impl;

import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.dao.UserDao;
import com.zgh.onlinevideo.domain.User;
import com.zgh.onlinevideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public PageInfo<User> getUserList() {

        List<User> userList = userDao.findUserAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList, 4);

        return pageInfo;
    }

    // 假的删除 修改字段 flag 0
    @Override
    public int delete(Integer uid) {

        User user = this.getUser(uid);
        user.setFlag(0);

        return userDao.updateUser(user);
    }

    @Override
    public User getUser(int uid) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(uid);

        return userDao.findUserByIds(list);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}
