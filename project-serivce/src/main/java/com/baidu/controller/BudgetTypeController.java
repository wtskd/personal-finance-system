package com.baidu.controller;

import com.baidu.pojo.BudgetType;
import com.baidu.service.BudgetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 预算类型 REST 控制器（服务提供者）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@RestController
@RequestMapping("/budgetType")
public class BudgetTypeController {

    @Autowired
    private BudgetTypeService budgetTypeService;

    @GetMapping("/getAll")
    public List<BudgetType> getAll() {
        System.out.println("-------getAll BudgetType-------");
        return budgetTypeService.list();
    }

    @GetMapping("/getById/{typeId}")
    public BudgetType getById(@PathVariable("typeId") Integer typeId) {
        System.out.println("-------getById BudgetType-------typeId:" + typeId);
        return budgetTypeService.getById(typeId);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody BudgetType budgetType) {
        System.out.println("-------add BudgetType-------");
        System.out.println("budgetType:" + budgetType);
        return budgetTypeService.save(budgetType);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody BudgetType budgetType) {
        System.out.println("-------update BudgetType-------");
        System.out.println("budgetType:" + budgetType);
        return budgetTypeService.updateById(budgetType);
    }

    @PostMapping("/deleteById/{typeId}")
    public boolean deleteById(@PathVariable("typeId") Integer typeId) {
        System.out.println("-------deleteById BudgetType-------typeId:" + typeId);
        return budgetTypeService.removeById(typeId);
    }

    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids) {
        System.out.println("-------deleteByIds BudgetType-------");
        for (Integer id : ids) {
            System.out.println("id:" + id);
        }
        return budgetTypeService.removeByIds(ids);
    }

}
