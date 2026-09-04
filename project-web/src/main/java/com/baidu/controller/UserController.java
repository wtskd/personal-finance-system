package com.baidu.controller;


import com.baidu.feignservice.RoleFeignService;
import com.baidu.feignservice.UserFeignService;
import com.baidu.pojo.Role;
import com.baidu.pojo.User;
import com.baidu.utils.PageResult;
import com.baidu.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RoleFeignService roleFeignService;
    @Autowired
    private UserFeignService userFeignService;

    @RequestMapping("/userList")
    @ResponseBody
    public PageResult<User> userList(@RequestParam(defaultValue = "") String pname,
                                     @RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "5") Integer limit,
                                     HttpSession session){
        System.out.println("----------userList------------");
        System.out.println("pname:"+pname+" page:"+page+" limit:"+limit);

        if ("".equals(pname)){
            pname=null;
        }

        User user=( User) session.getAttribute("user2");

        Map<String,Object> map=new HashMap<>();
        map.put("name",pname);
        map.put("currentPage",page);
        map.put("pageSize",limit);

        int roleId=user.getRoleId();
        PageResult<User> result=null;

        if (roleId==1) {
            result=userFeignService.getUserByPage(map);
        } else {
            int userId=user.getUid();
            result=userFeignService.getUserAndRoleById(userId);
        }
        return result;
    }

    @GetMapping("/toUserList")
    public String toUserList(HttpServletRequest request){
        System.out.println("----------toUserList------------");
        List<Role> list=roleFeignService.getAllRole();
        request.setAttribute("rolelist",list);
        return "user/List";
    }

    @RequestMapping("/login")
    @ResponseBody
    public R  login(User user, HttpSession session){
        System.out.println("----------login------------");
        System.out.println("user:"+user);
        //根据用户名，密码，角色登录
        User user2=userFeignService.login(user);
        if(user2!=null){ //登录成功
            //将当前登录的用户对象保存到session中
            session.setAttribute("user2",user2);
            return R.right();
        }else{//登录失败
            return R.error();
        }
    }

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request){
        System.out.println("-----------toLogin---------");
        List<Role> list=roleFeignService.getAllRole();
        request.setAttribute("rolelist",list);
        return "user/login";
    }

    // 兼容浏览器缓存中旧的 user/login.html 退出跳转路径
    @RequestMapping("/login.html")
    public String toLoginHtml(HttpServletRequest request){
        System.out.println("-----------login.html---------");
        List<Role> list=roleFeignService.getAllRole();
        request.setAttribute("rolelist",list);
        return "user/login";
    }

    // ========== 用户 CRUD ==========

    @RequestMapping("/toAdd")
    public String toAdd(HttpServletRequest request) {
        System.out.println("-------toAdd User-------");
        List<Role> list = roleFeignService.getAllRole();
        request.setAttribute("rolelist", list);
        return "user/add";
    }

    @PostMapping("/doAdd")
    @ResponseBody
    public R doAdd(User user) {
        System.out.println("-------doAdd User-------");
        System.out.println("user:" + user);
        boolean flag = userFeignService.add(user);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @RequestMapping("/toEdit/{uid}")
    public String toEdit(@PathVariable("uid") Integer uid, HttpServletRequest request) {
        System.out.println("-------toEdit User-------uid:" + uid);
        List<Role> list = roleFeignService.getAllRole();
        request.setAttribute("rolelist", list);
        PageResult<User> result = userFeignService.getUserAndRoleById(uid);
        if (result.getData() != null && result.getData().size() > 0) {
            request.setAttribute("user", result.getData().get(0));
        }
        return "user/edit";
    }

    @PostMapping("/doEdit")
    @ResponseBody
    public R doEdit(User user) {
        System.out.println("-------doEdit User-------");
        System.out.println("user:" + user);
        boolean flag = userFeignService.update(user);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @PostMapping("/doDel/{uid}")
    @ResponseBody
    public R deleteById(@PathVariable("uid") Integer uid) {
        System.out.println("-------deleteById User-------uid:" + uid);
        boolean result = userFeignService.deleteById(uid);
        if (result) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @PostMapping("/batchDel")
    @ResponseBody
    public R batchDel(@RequestParam(value = "userIds[]") Integer userIds[]) {
        System.out.println("-------batchDel User-------");
        List<Integer> list = new ArrayList<>();
        for (Integer id : userIds) {
            System.out.println("id:" + id);
            list.add(id);
        }
        boolean flag = userFeignService.deleteByIds(list);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }
}
