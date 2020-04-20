package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-27 16:41
 */
public interface RoleService {

    List<Role> findAll();

    void save(Role role);

    Role findById(Integer roleId);

    List<Permission> findRoleByIdAndAllPermission(Integer roleId);

    void addPermissionToRole(Integer roleId, Integer[] permissionIds);
}
