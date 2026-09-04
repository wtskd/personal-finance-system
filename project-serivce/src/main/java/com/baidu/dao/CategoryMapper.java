package com.baidu.dao;

import com.baidu.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 收支分类 Mapper 接口
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 分页查询（按分类名模糊搜索）
     */
    public List<Category> getCategoryByPage(@Param("categoryName") String categoryName,
                                             @Param("typeId") Integer typeId,
                                             @Param("start") Integer start,
                                             @Param("pageSize") Integer pageSize);

    /**
     * 统计总数
     */
    public int count(@Param("categoryName") String categoryName,
                     @Param("typeId") Integer typeId);

}
