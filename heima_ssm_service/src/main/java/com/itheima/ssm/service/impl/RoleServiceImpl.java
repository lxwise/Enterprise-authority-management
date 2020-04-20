package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.RoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-27 16:42
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(Integer roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(Integer roleId) {
        return roleDao.findRoleByIdAndAllPermission(roleId);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) {
        for(Integer permissionId:permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
