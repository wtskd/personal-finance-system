package com.baidu.dao;

import com.baidu.pojo.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  购物车 Mapper 接口
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-15
 */
@Mapper
public interface AccountMapper extends BaseMapper<Cart> {

}
