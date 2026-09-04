package com.baidu.service;

import com.baidu.pojo.FinanceAccount;
import com.baidu.utils.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 财务账户 服务类
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
public interface FinanceAccountService extends IService<FinanceAccount> {

    /**
     * 分页查询账户（带模糊搜索）
     */
    public PageResult<FinanceAccount> getAccountByPage(String accountName, Integer currentPage, Integer pageSize);

}
