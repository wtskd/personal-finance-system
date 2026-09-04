package com.baidu.controller;

import com.baidu.pojo.Transaction;
import com.baidu.service.TransactionService;
import com.baidu.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 交易记录 REST 控制器（服务提供者）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * 查询所有交易记录
     */
    @GetMapping("/getAll")
    public List<Transaction> getAll() {
        System.out.println("-------getAll Transaction-------");
        return transactionService.list();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/getById/{transactionId}")
    public Transaction getById(@PathVariable("transactionId") Integer transactionId) {
        System.out.println("-------getById Transaction-------transactionId:" + transactionId);
        return transactionService.getById(transactionId);
    }

    /**
     * 分页查询
     */
    @PostMapping("/getTransactionByPage")
    public PageResult<Transaction> getTransactionByPage(@RequestBody Map<String, Object> map) {
        System.out.println("-------getTransactionByPage-------");
        System.out.println("接收到的参数: " + map);
        try {
            // 安全获取 accountId（可能为 null 或各种数字类型）
            Object accountIdObj = map.get("accountId");
            Integer accountId = null;
            if (accountIdObj != null) {
                if (accountIdObj instanceof Integer) {
                    accountId = (Integer) accountIdObj;
                } else if (accountIdObj instanceof Number) {
                    accountId = ((Number) accountIdObj).intValue();
                } else if (accountIdObj instanceof String && !((String) accountIdObj).isEmpty()) {
                    accountId = Integer.parseInt((String) accountIdObj);
                }
            }

            // 安全获取 status
            Object statusObj = map.get("status");
            String status = null;
            if (statusObj != null && !"".equals(statusObj)) {
                status = String.valueOf(statusObj);
            }

            // 安全获取 currentPage
            Object currentPageObj = map.get("currentPage");
            Integer currentPage = 1;
            if (currentPageObj != null) {
                if (currentPageObj instanceof Integer) {
                    currentPage = (Integer) currentPageObj;
                } else if (currentPageObj instanceof Number) {
                    currentPage = ((Number) currentPageObj).intValue();
                } else if (currentPageObj instanceof String) {
                    currentPage = Integer.parseInt((String) currentPageObj);
                }
            }

            // 安全获取 pageSize
            Object pageSizeObj = map.get("pageSize");
            Integer pageSize = 5;
            if (pageSizeObj != null) {
                if (pageSizeObj instanceof Integer) {
                    pageSize = (Integer) pageSizeObj;
                } else if (pageSizeObj instanceof Number) {
                    pageSize = ((Number) pageSizeObj).intValue();
                } else if (pageSizeObj instanceof String) {
                    pageSize = Integer.parseInt((String) pageSizeObj);
                }
            }

            System.out.println("accountId:" + accountId + " status:" + status + " currentPage:" + currentPage + " pageSize:" + pageSize);
            return transactionService.getTransactionByPage(accountId, status, currentPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            PageResult<Transaction> errorResult = new PageResult<>();
            errorResult.setCode(1);
            errorResult.setMsg("查询异常: " + e.getMessage());
            errorResult.setCount(0);
            return errorResult;
        }
    }

    /**
     * 添加交易记录
     */
    @PostMapping("/add")
    public boolean add(@RequestBody Transaction transaction) {
        System.out.println("-------add Transaction-------");
        System.out.println("transaction:" + transaction);
        return transactionService.save(transaction);
    }

    /**
     * 修改交易记录
     */
    @PostMapping("/update")
    public boolean update(@RequestBody Transaction transaction) {
        System.out.println("-------update Transaction-------");
        System.out.println("transaction:" + transaction);
        return transactionService.updateById(transaction);
    }

    /**
     * 单个删除
     */
    @PostMapping("/deleteById/{transactionId}")
    public boolean deleteById(@PathVariable("transactionId") Integer transactionId) {
        System.out.println("-------deleteById Transaction-------transactionId:" + transactionId);
        return transactionService.removeById(transactionId);
    }

    /**
     * 批量删除
     */
    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids) {
        System.out.println("-------deleteByIds Transaction-------");
        for (Integer id : ids) {
            System.out.println("id:" + id);
        }
        return transactionService.removeByIds(ids);
    }

}
