package com.baidu.service.impl;

import com.baidu.dao.FinanceAccountMapper;
import com.baidu.dao.TransactionMapper;
import com.baidu.pojo.FinanceAccount;
import com.baidu.pojo.Transaction;
import com.baidu.service.TransactionService;
import com.baidu.utils.PageResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 交易记录 服务实现类
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    @Override
    public PageResult<Transaction> getTransactionByPage(Integer accountId, String status, Integer currentPage, Integer pageSize) {
        PageResult<Transaction> pageResult = new PageResult<>();
        pageResult.setCode(0);
        pageResult.setMsg("分页查询交易记录成功");

        // 计算总记录数
        int total = transactionMapper.count(accountId, status);
        pageResult.setCount(total);

        // 计算起始位置
        Integer start = (currentPage - 1) * pageSize;

        // 执行分页查询
        List<Transaction> list = transactionMapper.getTransactionByPage(accountId, status, start, pageSize);
        pageResult.setData(list);

        return pageResult;
    }

    /**
     * 添加交易记录，同步更新账户余额
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Transaction transaction) {
        // 查询关联账户
        FinanceAccount account = financeAccountMapper.selectById(transaction.getAccountId());
        if (account == null) {
            throw new RuntimeException("账户不存在，accountId=" + transaction.getAccountId());
        }

        BigDecimal amount = transaction.getAmount();
        if ("支出".equals(transaction.getStatus())) {
            // 支出：扣减余额
            account.setBalance(account.getBalance().subtract(amount));
        } else if ("收入".equals(transaction.getStatus())) {
            // 收入：增加余额
            account.setBalance(account.getBalance().add(amount));
        }

        // 更新账户余额
        financeAccountMapper.updateById(account);
        // 保存交易记录
        return super.save(transaction);
    }

    /**
     * 修改交易记录，同步更新账户余额
     * 先恢复旧记录的余额影响，再应用新记录的余额影响
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Transaction transaction) {
        // 查询旧记录
        Transaction old = this.getById(transaction.getTransactionId());
        if (old != null && old.getAccountId() != null) {
            FinanceAccount account = financeAccountMapper.selectById(old.getAccountId());
            if (account != null) {
                BigDecimal oldAmount = old.getAmount();
                BigDecimal newAmount = transaction.getAmount();
                String oldStatus = old.getStatus();
                String newStatus = transaction.getStatus();

                // 先恢复旧记录的影响（反向操作）
                if ("支出".equals(oldStatus)) {
                    account.setBalance(account.getBalance().add(oldAmount));
                } else if ("收入".equals(oldStatus)) {
                    account.setBalance(account.getBalance().subtract(oldAmount));
                }

                // 如果账户变了，切换到新账户
                if (transaction.getAccountId() != null && !transaction.getAccountId().equals(old.getAccountId())) {
                    financeAccountMapper.updateById(account);
                    account = financeAccountMapper.selectById(transaction.getAccountId());
                    if (account == null) {
                        throw new RuntimeException("新账户不存在，accountId=" + transaction.getAccountId());
                    }
                }

                // 再应用新记录的影响
                if ("支出".equals(newStatus)) {
                    account.setBalance(account.getBalance().subtract(newAmount));
                } else if ("收入".equals(newStatus)) {
                    account.setBalance(account.getBalance().add(newAmount));
                }

                financeAccountMapper.updateById(account);
            }
        }
        return super.updateById(transaction);
    }

    /**
     * 删除交易记录，同步恢复账户余额
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(java.io.Serializable id) {
        Transaction transaction = this.getById(id);
        if (transaction != null) {
            FinanceAccount account = financeAccountMapper.selectById(transaction.getAccountId());
            if (account != null) {
                BigDecimal amount = transaction.getAmount();
                if ("支出".equals(transaction.getStatus())) {
                    // 删除支出记录：恢复余额（加回来）
                    account.setBalance(account.getBalance().add(amount));
                } else if ("收入".equals(transaction.getStatus())) {
                    // 删除收入记录：扣减余额
                    account.setBalance(account.getBalance().subtract(amount));
                }
                financeAccountMapper.updateById(account);
            }
        }
        return super.removeById(id);
    }

    /**
     * 批量删除交易记录，逐笔恢复账户余额
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(java.util.Collection<?> list) {
        if (list != null) {
            for (Object id : list) {
                this.removeById((java.io.Serializable) id);
            }
        }
        return true;
    }

}
