package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-27 11:52
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //查询所有用户
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }
    //根据id查询用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(Integer id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo =userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //保存用户
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username =='tom'")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 查询可以添加的结算书
     * @param id
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(Integer id){
        ModelAndView mv = new ModelAndView();
        //1.根据id查询用户
        UserInfo userInfo = userService.findById(id);
        //2.根据用户id查询可以添加的权限
        List<Role> otherRoles = userService.findOtherRoles(id);
       mv.addObject("user",userInfo);
       mv.addObject("roleList",otherRoles);
       mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) Integer userId,
                                @RequestParam(name = "ids",required = true) Integer[] roleIds){

        userService.addRoleToUser(userId,roleIds);

        return "redirect:findAll.do";
    }
}
