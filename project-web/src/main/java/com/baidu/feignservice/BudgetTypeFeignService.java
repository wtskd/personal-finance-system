package com.baidu.feignservice;

import com.baidu.pojo.BudgetType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 预算类型 Feign 远程调用接口
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@FeignClient(value = "project-service", path = "/budgetType")
public interface BudgetTypeFeignService {

    @GetMapping("/getAll")
    public List<BudgetType> getAll();

    @GetMapping("/getById/{typeId}")
    public BudgetType getById(@PathVariable("typeId") Integer typeId);

    @PostMapping("/add")
    public boolean add(@RequestBody BudgetType budgetType);

    @PostMapping("/update")
    public boolean update(@RequestBody BudgetType budgetType);

    @PostMapping("/deleteById/{typeId}")
    public boolean deleteById(@PathVariable("typeId") Integer typeId);

    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids);

}
