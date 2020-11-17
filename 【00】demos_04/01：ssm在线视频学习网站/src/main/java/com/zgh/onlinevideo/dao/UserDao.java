package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    int insertUser(User user);

}
