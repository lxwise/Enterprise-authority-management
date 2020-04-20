package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-26 13:27
 */
public interface UserDao {
//多表查询根据用户名查询用户信息和对应的角色
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findByUserName(String username);

    //查询所有用户
    @Select("select * from users")
   public List<UserInfo> findAll();

    //添加用户
    @Insert("insert into users(username,password,email,phoneNum,status) values(#{username},#{password},#{email},#{phoneNum},#{status})")
   /* @SelectKey(keyProperty = "id",resultType = Integer.class,before = true,
            statement = "select replace(uuid())"
            )*/
    public void save(UserInfo userInfo);

    //根据用户id查询详情
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findById(Integer id);

    //查询可以添加的角色
    @Select("select * from role where id not in (select roleId from user_role where userId=#{id})")
    List<Role> findOtherRoles(Integer id);

    @Insert("insert into user_role(userId,roleId)values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
