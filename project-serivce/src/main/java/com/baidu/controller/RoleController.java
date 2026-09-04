package com.baidu.controller;

import com.baidu.pojo.Role;
import com.baidu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色 REST 控制器（服务提供者）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-03
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     */
    @GetMapping("/getAllRole")
    public List<Role> getAllRole() {
        System.out.println("-------getAllRole-------");
        return roleService.list();
    }

    /**
     * 根据ID查询角色
     */
    @GetMapping("/getById/{rid}")
    public Role getById(@PathVariable("rid") Integer rid) {
        System.out.println("-------getById Role-------rid:" + rid);
        return roleService.getById(rid);
    }

    /**
     * 添加角色
     */
    @PostMapping("/add")
    public boolean add(@RequestBody Role role) {
        System.out.println("-------add Role-------");
        System.out.println("role:" + role);
        return roleService.save(role);
    }

    /**
     * 修改角色
     */
    @PostMapping("/update")
    public boolean update(@RequestBody Role role) {
        System.out.println("-------update Role-------");
        System.out.println("role:" + role);
        return roleService.updateById(role);
    }

    /**
     * 单个删除
     */
    @PostMapping("/deleteById/{rid}")
    public boolean deleteById(@PathVariable("rid") Integer rid) {
        System.out.println("-------deleteById Role-------rid:" + rid);
        return roleService.removeById(rid);
    }

    /**
     * 批量删除
     */
    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids) {
        System.out.println("-------deleteByIds Role-------");
        for (Integer id : ids) {
            System.out.println("id:" + id);
        }
        return roleService.removeByIds(ids);
    }

}
