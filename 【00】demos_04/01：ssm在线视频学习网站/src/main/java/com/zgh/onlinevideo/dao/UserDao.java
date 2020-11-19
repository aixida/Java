package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface UserDao {

    int insertUser(User user);

    User findUserByCondition(HashMap<String, Object> map);

}
