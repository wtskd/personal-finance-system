package com.baidu.controller;


import com.baidu.feignservice.TransactionFeignService;
import com.baidu.pojo.Transaction;
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
 * 交易记录 前端控制器（服务消费者）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionFeignService transactionFeignService;

    /**
     * 跳转到交易记录列表页面
     */
    @RequestMapping("/toList")
    public String toList() {
        System.out.println("-------toList Transaction-------");
        return "transaction/list";
    }

    /**
     * 分页查询交易记录
     */
    @PostMapping("/getPage")
    @ResponseBody
    public PageResult<Transaction> getPage(@RequestParam(defaultValue = "") String status,
                                            @RequestParam(defaultValue = "") String accountIdStr,
                                            @RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer limit) {
        System.out.println("-------getPage Transaction-------");
        System.out.println("status:" + status + " accountIdStr:" + accountIdStr + " page:" + page + " limit:" + limit);

        if ("".equals(status)) {
            status = null;
        }
        Integer accountId = null;
        if (!"".equals(accountIdStr)) {
            accountId = Integer.parseInt(accountIdStr);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("accountId", accountId);
        map.put("status", status);
        map.put("currentPage", page);
        map.put("pageSize", limit);

        return transactionFeignService.getTransactionByPage(map);
    }

    /**
     * 跳转到添加页面
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        System.out.println("-------toAdd Transaction-------");
        return "transaction/add";
    }

    /**
     * 执行添加
     */
    @PostMapping("/doAdd")
    @ResponseBody
    public R doAdd(Transaction transaction) {
        System.out.println("-------doAdd Transaction-------");
        System.out.println("transaction:" + transaction);
        boolean flag = transactionFeignService.add(transaction);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    /**
     * 跳转到编辑页面
     */
    @RequestMapping("/toEdit/{transactionId}")
    public String toEdit(@PathVariable("transactionId") Integer transactionId, HttpServletRequest request) {
        System.out.println("-------toEdit Transaction-------transactionId:" + transactionId);
        Transaction transaction = transactionFeignService.getById(transactionId);
        request.setAttribute("transaction", transaction);
        return "transaction/edit";
    }

    /**
     * 执行修改
     */
    @PostMapping("/doEdit")
    @ResponseBody
    public R doEdit(Transaction transaction) {
        System.out.println("-------doEdit Transaction-------");
        System.out.println("transaction:" + transaction);
        boolean flag = transactionFeignService.update(transaction);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

    /**
     * 单个删除
     */
    @PostMapping("/doDel/{transactionId}")
    @ResponseBody
    public R deleteById(@PathVariable("transactionId") Integer transactionId) {
        System.out.println("-------deleteById Transaction-------transactionId:" + transactionId);
        boolean result = transactionFeignService.deleteById(transactionId);
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
    public R batchDel(@RequestParam(value = "transactionIds[]", required = false) Integer transactionIds[]) {
        System.out.println("-------batchDel Transaction-------");
        if (transactionIds == null || transactionIds.length == 0) {
            return R.error();
        }
        List<Integer> list = new ArrayList<>();
        for (Integer id : transactionIds) {
            System.out.println("id:" + id);
            list.add(id);
        }
        boolean flag = transactionFeignService.deleteByIds(list);
        if (flag) {
            return R.right();
        } else {
            return R.error();
        }
    }

}
