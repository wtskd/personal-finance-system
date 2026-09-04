package com.baidu.service.impl;

import com.baidu.dao.CategoryMapper;
import com.baidu.pojo.Category;
import com.baidu.service.CategoryService;
import com.baidu.utils.PageResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收支分类 服务实现类
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult<Category> getCategoryByPage(String categoryName, Integer typeId, Integer currentPage, Integer pageSize) {
        PageResult<Category> pageResult = new PageResult<>();
        pageResult.setCode(0);
        pageResult.setMsg("分页查询分类成功");

        int total = categoryMapper.count(categoryName, typeId);
        pageResult.setCount(total);

        Integer start = (currentPage - 1) * pageSize;

        List<Category> list = categoryMapper.getCategoryByPage(categoryName, typeId, start, pageSize);
        pageResult.setData(list);

        return pageResult;
    }

}
