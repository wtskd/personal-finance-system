package com.baidu.controller;


import com.baidu.feignservice.CategoryFeignService;
import com.baidu.pojo.Category;
import com.baidu.utils.PageResult;
import com.baidu.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 收支分类 前端控制器（服务消费者）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryFeignService categoryFeignService;

    @RequestMapping("/toList")
    public String toList() {
        System.out.println("-------toList Category-------");
        return "category/list";
    }

    @PostMapping("/getPage")
    @ResponseBody
    public PageResult<Category> getPage(@RequestParam(defaultValue = "") String categoryName,
                                         @RequestParam(defaultValue = "") String typeIdStr,
                                         @RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "5") Integer limit) {
        System.out.println("-------getPage Category-------");
        System.out.println("categoryName:" + categoryName + " typeIdStr:" + typeIdStr + " page:" + page + " limit:" + limit);

        if ("".equals(categoryName)) {
            categoryName = null;
        }
        Integer typeId = null;
        if (!"".equals(typeIdStr)) {
            typeId = Integer.parseInt(typeIdStr);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", categoryName);
        map.put("typeId", typeId);
        map.put("currentPage", page);
        map.put("pageSize", limit);

        return categoryFeignService.getCategoryByPage(map);
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        System.out.println("-------toAdd Category-------");
        return "category/add";
    }

    @PostMapping("/doAdd")
    @ResponseBody
    public R doAdd(Category category) {
        System.out.println("-------doAdd Category-------");
        System.out.println("category:" + category);
        boolean flag = categoryFeignService.add(category);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @RequestMapping("/toEdit/{categoryId}")
    public String toEdit(@PathVariable("categoryId") Integer categoryId, HttpServletRequest request) {
        System.out.println("-------toEdit Category-------categoryId:" + categoryId);
        Category category = categoryFeignService.getById(categoryId);
        request.setAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/doEdit")
    @ResponseBody
    public R doEdit(Category category) {
        System.out.println("-------doEdit Category-------");
        System.out.println("category:" + category);
        boolean flag = categoryFeignService.update(category);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @PostMapping("/doDel/{categoryId}")
    @ResponseBody
    public R deleteById(@PathVariable("categoryId") Integer categoryId) {
        System.out.println("-------deleteById Category-------categoryId:" + categoryId);
        boolean result = categoryFeignService.deleteById(categoryId);
        if (result) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @PostMapping("/batchDel")
    @ResponseBody
    public R batchDel(@RequestParam(value = "categoryIds[]", required = false) Integer categoryIds[]) {
        System.out.println("-------batchDel Category-------");
        if (categoryIds == null || categoryIds.length == 0) {
            return R.error();
        }
        List<Integer> list = new ArrayList<>();
        for (Integer id : categoryIds) {
            System.out.println("id:" + id);
            list.add(id);
        }
        boolean flag = categoryFeignService.deleteByIds(list);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

}
