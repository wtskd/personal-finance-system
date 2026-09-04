package com.baidu.controller;


import com.baidu.feignservice.AccountFeignService;
import com.baidu.pojo.FinanceAccount;
import com.baidu.utils.PageResult;
import com.baidu.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 财务账户 前端控制器（服务消费者）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountFeignService accountFeignService;

    /**
     * 跳转到账户列表页面
     */
    @RequestMapping("/toList")
    public String toList() {
        System.out.println("-------toList Account-------");
        return "account/list";
    }

    /**
     * 分页查询账户
     */
    @PostMapping("/getPage")
    @ResponseBody
    public PageResult<FinanceAccount> getPage(@RequestParam(defaultValue = "") String accountName,
                                               @RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "5") Integer limit) {
        System.out.println("-------getPage Account-------");
        System.out.println("accountName:" + accountName + " page:" + page + " limit:" + limit);

        if ("".equals(accountName)) {
            accountName = null;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("accountName", accountName);
        map.put("currentPage", page);
        map.put("pageSize", limit);

        return accountFeignService.getAccountByPage(map);
    }

    /**
     * 跳转到添加页面
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        System.out.println("-------toAdd Account-------");
        return "account/add";
    }

    /**
     * 执行添加
     */
    @PostMapping("/doAdd")
    @ResponseBody
    public R doAdd(FinanceAccount financeAccount) {
        System.out.println("-------doAdd Account-------");
        System.out.println("financeAccount:" + financeAccount);
        boolean flag = accountFeignService.add(financeAccount);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    /**
     * 跳转到编辑页面
     */
    @RequestMapping("/toEdit/{accountId}")
    public String toEdit(@PathVariable("accountId") Integer accountId, HttpServletRequest request) {
        System.out.println("-------toEdit Account-------accountId:" + accountId);
        FinanceAccount financeAccount = accountFeignService.getById(accountId);
        request.setAttribute("account", financeAccount);
        return "account/edit";
    }

    /**
     * 执行修改
     */
    @PostMapping("/doEdit")
    @ResponseBody
    public R doEdit(FinanceAccount financeAccount) {
        System.out.println("-------doEdit Account-------");
        System.out.println("financeAccount:" + financeAccount);
        boolean flag = accountFeignService.update(financeAccount);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    /**
     * 单个删除
     */
    @PostMapping("/doDel/{accountId}")
    @ResponseBody
    public R deleteById(@PathVariable("accountId") Integer accountId) {
        System.out.println("-------deleteById Account-------accountId:" + accountId);
        boolean result = accountFeignService.deleteById(accountId);
        if (result) {
            return R.right();
        } else {
            return R.error();
        }
    }

    /**
     * 批量删除
     */
    @PostMapping("/batchDel")
    @ResponseBody
    public R batchDel(@RequestParam(value = "accountIds[]", required = false) Integer accountIds[]) {
        System.out.println("-------batchDel Account-------");
        if (accountIds == null || accountIds.length == 0) {
            return R.error();
        }
        List<Integer> list = new ArrayList<>();
        for (Integer id : accountIds) {
            System.out.println("id:" + id);
            list.add(id);
        }
        boolean flag = accountFeignService.deleteByIds(list);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

}
