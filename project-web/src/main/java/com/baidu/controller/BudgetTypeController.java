package com.baidu.controller;


import com.baidu.feignservice.BudgetTypeFeignService;
import com.baidu.pojo.BudgetType;
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
 * 预算类型 前端控制器（服务消费者）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@Controller
@RequestMapping("/budget-type")
public class BudgetTypeController {

    @Autowired
    private BudgetTypeFeignService budgetTypeFeignService;

    @RequestMapping("/toList")
    public String toList() {
        System.out.println("-------toList BudgetType-------");
        return "budgetType/list";
    }

    /**
     * 供 Layui 表格调用的分页接口
     */
    @PostMapping("/getAllForTable")
    @ResponseBody
    public PageResult<BudgetType> getAllForTable(@RequestParam(defaultValue = "1") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer limit) {
        System.out.println("-------getAllForTable BudgetType-------");
        List<BudgetType> allList = budgetTypeFeignService.getAll();
        PageResult<BudgetType> result = new PageResult<>();
        result.setCode(0);
        result.setMsg("查询成功");
        result.setCount(allList.size());

        // 手动分页
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
        System.out.println("-------toAdd BudgetType-------");
        return "budgetType/add";
    }

    @PostMapping("/doAdd")
    @ResponseBody
    public R doAdd(BudgetType budgetType) {
        System.out.println("-------doAdd BudgetType-------");
        System.out.println("budgetType:" + budgetType);
        boolean flag = budgetTypeFeignService.add(budgetType);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @RequestMapping("/toEdit/{typeId}")
    public String toEdit(@PathVariable("typeId") Integer typeId, HttpServletRequest request) {
        System.out.println("-------toEdit BudgetType-------typeId:" + typeId);
        BudgetType budgetType = budgetTypeFeignService.getById(typeId);
        request.setAttribute("budgetType", budgetType);
        return "budgetType/edit";
    }

    @PostMapping("/doEdit")
    @ResponseBody
    public R doEdit(BudgetType budgetType) {
        System.out.println("-------doEdit BudgetType-------");
        System.out.println("budgetType:" + budgetType);
        boolean flag = budgetTypeFeignService.update(budgetType);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @PostMapping("/doDel/{typeId}")
    @ResponseBody
    public R deleteById(@PathVariable("typeId") Integer typeId) {
        System.out.println("-------deleteById BudgetType-------typeId:" + typeId);
        boolean result = budgetTypeFeignService.deleteById(typeId);
        if (result) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @PostMapping("/batchDel")
    @ResponseBody
    public R batchDel(@RequestParam(value = "typeIds[]", required = false) Integer typeIds[]) {
        System.out.println("-------batchDel BudgetType-------");
        if (typeIds == null || typeIds.length == 0) {
            return R.error();
        }
        List<Integer> list = new ArrayList<>();
        for (Integer id : typeIds) {
            System.out.println("id:" + id);
            list.add(id);
        }
        boolean flag = budgetTypeFeignService.deleteByIds(list);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

}
