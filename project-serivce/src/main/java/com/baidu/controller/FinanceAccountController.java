package com.baidu.controller;

import com.baidu.pojo.FinanceAccount;
import com.baidu.service.FinanceAccountService;
import com.baidu.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 财务账户 REST 控制器（服务提供者）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@RestController
@RequestMapping("/account")
public class FinanceAccountController {

    @Autowired
    private FinanceAccountService financeAccountService;

    /**
     * 查询所有账户
     */
    @GetMapping("/getAll")
    public List<FinanceAccount> getAll() {
        System.out.println("-------getAll Account-------");
        return financeAccountService.list();
    }

    /**
     * 根据ID查询账户
     */
    @GetMapping("/getById/{accountId}")
    public FinanceAccount getById(@PathVariable("accountId") Integer accountId) {
        System.out.println("-------getById Account-------accountId:" + accountId);
        return financeAccountService.getById(accountId);
    }

    /**
     * 分页查询账户
     */
    @PostMapping("/getAccountByPage")
    public PageResult<FinanceAccount> getAccountByPage(@RequestBody Map<String, Object> map) {
        System.out.println("-------getAccountByPage-------");
        String accountName = (String) map.get("accountName");
        Object currentPageObj = map.get("currentPage");
        Object pageSizeObj = map.get("pageSize");
        Integer currentPage = (currentPageObj instanceof Integer) ? (Integer) currentPageObj : ((Number) currentPageObj).intValue();
        Integer pageSize = (pageSizeObj instanceof Integer) ? (Integer) pageSizeObj : ((Number) pageSizeObj).intValue();
        System.out.println("accountName:" + accountName + " currentPage:" + currentPage + " pageSize:" + pageSize);
        return financeAccountService.getAccountByPage(accountName, currentPage, pageSize);
    }

    /**
     * 添加账户
     */
    @PostMapping("/add")
    public boolean add(@RequestBody FinanceAccount financeAccount) {
        System.out.println("-------add Account-------");
        System.out.println("financeAccount:" + financeAccount);
        return financeAccountService.save(financeAccount);
    }

    /**
     * 修改账户
     */
    @PostMapping("/update")
    public boolean update(@RequestBody FinanceAccount financeAccount) {
        System.out.println("-------update Account-------");
        System.out.println("financeAccount:" + financeAccount);
        return financeAccountService.updateById(financeAccount);
    }

    /**
     * 单个删除
     */
    @PostMapping("/deleteById/{accountId}")
    public boolean deleteById(@PathVariable("accountId") Integer accountId) {
        System.out.println("-------deleteById Account-------accountId:" + accountId);
        return financeAccountService.removeById(accountId);
    }

    /**
     * 批量删除
     */
    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids) {
        System.out.println("-------deleteByIds Account-------");
        for (Integer id : ids) {
            System.out.println("id:" + id);
        }
        return financeAccountService.removeByIds(ids);
    }

}
