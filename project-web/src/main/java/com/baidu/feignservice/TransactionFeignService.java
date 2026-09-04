package com.baidu.feignservice;

import com.baidu.pojo.Transaction;
import com.baidu.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 交易记录 Feign 远程调用接口
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@FeignClient(value = "project-service", path = "/transaction")
public interface TransactionFeignService {

    @GetMapping("/getAll")
    public List<Transaction> getAll();

    @GetMapping("/getById/{transactionId}")
    public Transaction getById(@PathVariable("transactionId") Integer transactionId);

    @PostMapping("/getTransactionByPage")
    public PageResult<Transaction> getTransactionByPage(@RequestBody Map<String, Object> map);

    @PostMapping("/add")
    public boolean add(@RequestBody Transaction transaction);

    @PostMapping("/update")
    public boolean update(@RequestBody Transaction transaction);

    @PostMapping("/deleteById/{transactionId}")
    public boolean deleteById(@PathVariable("transactionId") Integer transactionId);

    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids);

}
