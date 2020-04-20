package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-26 15:44
 */
public interface RoleDao {

    //根据局用户id查询所有对应的角色
    @Select("select * from role where id in (select roleId from user_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(Integer userId);

    //查询所有角色
    @Select("select * from role")
    List<Role> findAll();

    //保存角色
    @Insert("insert into role(roleName,roleDesc)value(#{roleName},#{roleDesc})")
    void save(Role role);
    //根据id查询角色
    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(Integer roleId);
    //根据id查询可以添加的权限
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findRoleByIdAndAllPermission(Integer roleId);

    //添加权限
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
}
