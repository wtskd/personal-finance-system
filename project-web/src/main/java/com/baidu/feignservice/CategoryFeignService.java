package com.baidu.feignservice;

import com.baidu.pojo.Category;
import com.baidu.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 收支分类 Feign 远程调用接口
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@FeignClient(value = "project-service", path = "/category")
public interface CategoryFeignService {

    @GetMapping("/getAll")
    public List<Category> getAll();

    @GetMapping("/getById/{categoryId}")
    public Category getById(@PathVariable("categoryId") Integer categoryId);

    @PostMapping("/getCategoryByPage")
    public PageResult<Category> getCategoryByPage(@RequestBody Map<String, Object> map);

    @PostMapping("/add")
    public boolean add(@RequestBody Category category);

    @PostMapping("/update")
    public boolean update(@RequestBody Category category);

    @PostMapping("/deleteById/{categoryId}")
    public boolean deleteById(@PathVariable("categoryId") Integer categoryId);

    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids);

}
