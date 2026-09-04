package com.baidu.service;

import com.baidu.pojo.Transaction;
import com.baidu.utils.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 交易记录 服务类
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
public interface TransactionService extends IService<Transaction> {

    /**
     * 分页查询交易记录
     */
    public PageResult<Transaction> getTransactionByPage(Integer accountId, String status, Integer currentPage, Integer pageSize);

}
