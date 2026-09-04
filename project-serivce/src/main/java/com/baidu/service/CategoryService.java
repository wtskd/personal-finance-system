package com.baidu.service;

import com.baidu.pojo.Category;
import com.baidu.utils.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 收支分类 服务类
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
public interface CategoryService extends IService<Category> {

    /**
     * 分页查询分类
     */
    public PageResult<Category> getCategoryByPage(String categoryName, Integer typeId, Integer currentPage, Integer pageSize);

}
