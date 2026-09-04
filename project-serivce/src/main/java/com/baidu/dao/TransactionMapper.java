package com.baidu.dao;

import com.baidu.pojo.Transaction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 交易记录 Mapper 接口
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@Mapper
public interface TransactionMapper extends BaseMapper<Transaction> {

    /**
     * 分页查询交易记录（关联账户和分类）
     */
    public List<Transaction> getTransactionByPage(@Param("accountId") Integer accountId,
                                                   @Param("status") String status,
                                                   @Param("start") Integer start,
                                                   @Param("pageSize") Integer pageSize);

    /**
     * 统计总数（带条件）
     */
    public int count(@Param("accountId") Integer accountId,
                     @Param("status") String status);

}
