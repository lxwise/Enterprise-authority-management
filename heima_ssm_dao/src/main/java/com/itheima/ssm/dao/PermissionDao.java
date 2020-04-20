package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-27 14:35
 */
public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId(Integer id);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
