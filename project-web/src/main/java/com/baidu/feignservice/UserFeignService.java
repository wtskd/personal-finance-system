package com.baidu.feignservice;

import com.baidu.pojo.User;
import com.baidu.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(value = "project-service",path = "/user")
public interface UserFeignService {
    @PostMapping("/getUserByPage")
    public PageResult<User> getUserByPage(@RequestBody Map<String,Object> map);

    @GetMapping("/getUserAndRoleById/{id}")
    public PageResult<User> getUserAndRoleById(@PathVariable("id") Integer id);

    //根据用户名，密码，角色实现登录
    @PostMapping("/login")
    public User login(@RequestBody User user);

    @PostMapping("/add")
    public boolean add(@RequestBody User user);

    @PostMapping("/update")
    public boolean update(@RequestBody User user);

    @PostMapping("/deleteById/{uid}")
    public boolean deleteById(@PathVariable("uid") Integer uid);

    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids);
}
