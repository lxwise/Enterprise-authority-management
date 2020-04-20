package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.UserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-26 20:23
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo =userDao.findByUserName(username);

        //处理自己的用户对象封装为UserDetails  "{noop}"+
        User user =new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()== 0 ? false:true,true,true,true,
                getAuthority(userInfo.getRoles()));


        return user;
    }
    //作用就是返回一个List集合，集合装的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }
    //查询所有用户
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    //添加用户
    @Override
    @Transactional
    public void save(UserInfo userInfo) {
        //springsecurity方式加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        //MD5方式加密
     /*   String md5password = DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes());
        userInfo.setPassword(md5password);*/
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(Integer id) {
        return userDao.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) {
        for (Integer roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
