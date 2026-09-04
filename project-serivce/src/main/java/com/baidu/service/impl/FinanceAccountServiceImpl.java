package com.baidu.service.impl;

import com.baidu.dao.FinanceAccountMapper;
import com.baidu.pojo.FinanceAccount;
import com.baidu.service.FinanceAccountService;
import com.baidu.utils.PageResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 财务账户 服务实现类
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@Service
public class FinanceAccountServiceImpl extends ServiceImpl<FinanceAccountMapper, FinanceAccount> implements FinanceAccountService {

    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    @Override
    public PageResult<FinanceAccount> getAccountByPage(String accountName, Integer currentPage, Integer pageSize) {
        PageResult<FinanceAccount> pageResult = new PageResult<>();
        pageResult.setCode(0);
        pageResult.setMsg("分页查询账户成功");

        // 计算总记录数
        int total = financeAccountMapper.count(accountName);
        pageResult.setCount(total);

        // 计算起始位置
        Integer start = (currentPage - 1) * pageSize;

        // 执行分页查询
        List<FinanceAccount> list = financeAccountMapper.getAccountByPage(accountName, start, pageSize);
        pageResult.setData(list);

        return pageResult;
    }

}
