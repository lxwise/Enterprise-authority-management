package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.RoleService;
import com.itheima.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-27 16:37
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //角色详情查询
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) Integer roleId){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);

        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }
    /**
     * 根据roleId查询角色，并查询出可以添加的权限
     * @param roleId
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(Integer roleId){
        ModelAndView mv = new  ModelAndView();
        //1.根据roleId查询role
        Role role = roleService.findById(roleId);
        //2.根据roleId查询出可以添加的权限
        List<Permission> otherPermissions=roleService.findRoleByIdAndAllPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }
    //给角色添加权限
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) Integer roleId,
                                      @RequestParam(name = "ids",required = true) Integer[] permissionIds){
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }
}
