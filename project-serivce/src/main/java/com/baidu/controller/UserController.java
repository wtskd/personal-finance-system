package com.baidu.controller;

import com.baidu.pojo.User;
import com.baidu.service.UserService;
import com.baidu.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserAndRoleById/{id}")
    public PageResult<User> getUserById(@PathVariable("id")Integer id){
        System.out.println("-----getUserById-----");
        System.out.println("id:"+id);
        PageResult<User> pageResult=userService.getUserAndRoleById(id);
        return pageResult;
    }

    @PostMapping("/getUserByPage")
    public PageResult<User> getUserByPage(@RequestBody Map<String,Object> map){
        System.out.println("-----getUserByPage-----");
        String name=(String)map.get("name");
        Integer currentPage=(Integer)map.get("currentPage");
        Integer pageSize=(Integer)map.get("pageSize");

        PageResult<User> pageResult=userService.getUserByPage(name,currentPage,pageSize);
        return pageResult;

    }
    //根据用户名，密码，角色实现登录
    @PostMapping("/login")
    public User login(@RequestBody User user){
        System.out.println("-------login--------");
        //执行登录
        User user2=userService.login(user);
        return user2;
    }

    @PostMapping("/add")
    public boolean add(@RequestBody User user) {
        System.out.println("-------add User-------");
        System.out.println("user:" + user);
        return userService.save(user);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody User user) {
        System.out.println("-------update User-------");
        System.out.println("user:" + user);
        return userService.updateById(user);
    }

    @PostMapping("/deleteById/{uid}")
    public boolean deleteById(@PathVariable("uid") Integer uid) {
        System.out.println("-------deleteById User-------uid:" + uid);
        return userService.removeById(uid);
    }

    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids) {
        System.out.println("-------deleteByIds User-------");
        for (Integer id : ids) {
            System.out.println("id:" + id);
        }
        return userService.removeByIds(ids);
    }
}
