package com.baidu.service.impl;

import com.baidu.dao.RoleMapper;
import com.baidu.pojo.Role;
import com.baidu.pojo.User;
import com.baidu.dao.UserMapper;
import com.baidu.service.UserService;
import com.baidu.utils.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    public PageResult<User> getUserAndRoleById(int uid){
        User user=userMapper.selectById(uid);
        int roleId=user.getRoleId();
        Role role=roleMapper.selectById(roleId);
        user.setMyRole(role);
        List<User> list=new ArrayList<>();
        list.add(user);
        PageResult<User> PageResult=new PageResult<>();
        PageResult.setCode(0);
        PageResult.setMsg("分页查询成功");
        PageResult.setCount(1);
        PageResult.setData(list);
        return PageResult;

    }

    public PageResult<User> getUserByPage(String name, Integer currentPage, Integer pageSize){
        int start=(currentPage-1)*pageSize;
        List<User> list=userMapper.getUserByPage(name,start,pageSize);
        int total=userMapper.mycount(name);
        PageResult<User> PageResult=new PageResult<>();
        PageResult.setCode(0);
        PageResult.setMsg("分页查询成功");
        PageResult.setCount(total);
        PageResult.setData(list);
        return PageResult;
    }

    public int mycount(String name){
        return userMapper.mycount(name);
    }

    //根据用户名，密码，角色登录
    public User login(User user){
        //创建QueryWrapper对象，用于封装复杂条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",user.getName());
        queryWrapper.eq("password",user.getPassword());
        queryWrapper.eq("role_id",user.getRoleId());
        //执行查询
        User user2=userMapper.selectOne(queryWrapper);
        return user2;
}

}
