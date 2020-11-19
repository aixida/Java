package com.zgh.onlinevideo.service;

import com.zgh.onlinevideo.domain.User;

public interface UserService {

    User login(User user);

    int regist(User user);

    User existEmail(String email);

}
