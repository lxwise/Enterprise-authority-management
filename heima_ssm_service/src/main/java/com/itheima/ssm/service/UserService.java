package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-26 20:23
 */
public interface UserService extends UserDetailsService {
    //查询所有用户
    public List<UserInfo> findAll();

    public void save(UserInfo userInfo);

    public UserInfo findById(Integer id);

    List<Role> findOtherRoles(Integer id);

    void addRoleToUser(Integer userId, Integer[] roleIds);
}
