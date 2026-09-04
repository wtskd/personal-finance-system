package com.baidu.controller;

import com.baidu.pojo.Category;
import com.baidu.service.CategoryService;
import com.baidu.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 收支分类 REST 控制器（服务提供者）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public List<Category> getAll() {
        System.out.println("-------getAll Category-------");
        return categoryService.list();
    }

    @GetMapping("/getById/{categoryId}")
    public Category getById(@PathVariable("categoryId") Integer categoryId) {
        System.out.println("-------getById Category-------categoryId:" + categoryId);
        return categoryService.getById(categoryId);
    }

    @PostMapping("/getCategoryByPage")
    public PageResult<Category> getCategoryByPage(@RequestBody Map<String, Object> map) {
        System.out.println("-------getCategoryByPage-------");
        String categoryName = (String) map.get("categoryName");
        Object typeIdObj = map.get("typeId");
        Integer typeId = null;
        if (typeIdObj != null) {
            typeId = (typeIdObj instanceof Integer) ? (Integer) typeIdObj : ((Number) typeIdObj).intValue();
        }
        Object currentPageObj = map.get("currentPage");
        Object pageSizeObj = map.get("pageSize");
        Integer currentPage = (currentPageObj instanceof Integer) ? (Integer) currentPageObj : ((Number) currentPageObj).intValue();
        Integer pageSize = (pageSizeObj instanceof Integer) ? (Integer) pageSizeObj : ((Number) pageSizeObj).intValue();
        System.out.println("categoryName:" + categoryName + " typeId:" + typeId + " currentPage:" + currentPage + " pageSize:" + pageSize);
        return categoryService.getCategoryByPage(categoryName, typeId, currentPage, pageSize);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Category category) {
        System.out.println("-------add Category-------");
        System.out.println("category:" + category);
        return categoryService.save(category);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Category category) {
        System.out.println("-------update Category-------");
        System.out.println("category:" + category);
        return categoryService.updateById(category);
    }

    @PostMapping("/deleteById/{categoryId}")
    public boolean deleteById(@PathVariable("categoryId") Integer categoryId) {
        System.out.println("-------deleteById Category-------categoryId:" + categoryId);
        return categoryService.removeById(categoryId);
    }

    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids) {
        System.out.println("-------deleteByIds Category-------");
        for (Integer id : ids) {
            System.out.println("id:" + id);
        }
        return categoryService.removeByIds(ids);
    }

}
