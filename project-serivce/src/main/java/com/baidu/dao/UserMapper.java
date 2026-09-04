package com.baidu.dao;

import com.baidu.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    public List<User> getUserByPage(@Param("na") String name,
                                    @Param("st") Integer start, @Param("pagesize") Integer pageSize);
    public int mycount(String name);

}
