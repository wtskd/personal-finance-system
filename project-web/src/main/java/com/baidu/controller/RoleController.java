package com.baidu.controller;


import com.baidu.feignservice.RoleFeignService;
import com.baidu.pojo.Role;
import com.baidu.utils.PageResult;
import com.baidu.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色管理 前端控制器（服务消费者）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-03
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleFeignService roleFeignService;

    @RequestMapping("/toRoleList")
    public String toRoleList() {
        System.out.println("-------toRoleList Role-------");
        return "role/list";
    }

    /**
     * 供 Layui 表格调用的分页接口
     */
    @PostMapping("/getAllForTable")
    @ResponseBody
    public PageResult<Role> getAllForTable(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer limit) {
        System.out.println("-------getAllForTable Role-------");
        List<Role> allList = roleFeignService.getAllRole();
        PageResult<Role> result = new PageResult<>();
        result.setCode(0);
        result.setMsg("查询成功");
        result.setCount(allList.size());

        int start = (page - 1) * limit;
        int end = Math.min(start + limit, allList.size());
        if (start >= allList.size()) {
            result.setData(new ArrayList<>());
        } else {
            result.setData(allList.subList(start, end));
        }
        return result;
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        System.out.println("-------toAdd Role-------");
        return "role/add";
    }

    @PostMapping("/doAdd")
    @ResponseBody
    public R doAdd(Role role) {
        System.out.println("-------doAdd Role-------");
        System.out.println("role:" + role);
        boolean flag = roleFeignService.add(role);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @RequestMapping("/toEdit/{rid}")
    public String toEdit(@PathVariable("rid") Integer rid, HttpServletRequest request) {
        System.out.println("-------toEdit Role-------rid:" + rid);
        Role role = roleFeignService.getById(rid);
        request.setAttribute("role", role);
        return "role/edit";
    }

    @PostMapping("/doEdit")
    @ResponseBody
    public R doEdit(Role role) {
        System.out.println("-------doEdit Role-------");
        System.out.println("role:" + role);
        boolean flag = roleFeignService.update(role);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @PostMapping("/doDel/{rid}")
    @ResponseBody
    public R deleteById(@PathVariable("rid") Integer rid) {
        System.out.println("-------deleteById Role-------rid:" + rid);
        boolean result = roleFeignService.deleteById(rid);
        if (result) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @PostMapping("/batchDel")
    @ResponseBody
    public R batchDel(@RequestParam(value = "roleIds[]") Integer roleIds[]) {
        System.out.println("-------batchDel Role-------");
        List<Integer> list = new ArrayList<>();
        for (Integer id : roleIds) {
            System.out.println("id:" + id);
            list.add(id);
        }
        boolean flag = roleFeignService.deleteByIds(list);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

}
