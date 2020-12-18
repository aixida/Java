package com.zgh.onlinevideo.service;

import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.domain.User;

public interface UserService {

    User login(User user);

    int regist(User user);

    User existEmail(String email);

    PageInfo<User> getUserList();

    int delete(Integer uid);

    User getUser(int uid);

    int updateUser(User user);

}
