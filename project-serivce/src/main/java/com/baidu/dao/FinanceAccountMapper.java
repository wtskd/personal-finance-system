package com.baidu.dao;

import com.baidu.pojo.FinanceAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 财务账户 Mapper 接口
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@Mapper
public interface FinanceAccountMapper extends BaseMapper<FinanceAccount> {

    /**
     * 分页查询账户（关联预算类型）
     */
    public List<FinanceAccount> getAccountByPage(@Param("accountName") String accountName,
                                                  @Param("start") Integer start,
                                                  @Param("pageSize") Integer pageSize);

    /**
     * 统计账户总数（带模糊查询）
     */
    public int count(@Param("accountName") String accountName);

}
