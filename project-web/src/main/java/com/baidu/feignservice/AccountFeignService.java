package com.baidu.feignservice;

import com.baidu.pojo.FinanceAccount;
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
 * 财务账户 Feign 远程调用接口
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@FeignClient(value = "project-service", path = "/account")
public interface AccountFeignService {

    @GetMapping("/getAll")
    public List<FinanceAccount> getAll();

    @GetMapping("/getById/{accountId}")
    public FinanceAccount getById(@PathVariable("accountId") Integer accountId);

    @PostMapping("/getAccountByPage")
    public PageResult<FinanceAccount> getAccountByPage(@RequestBody Map<String, Object> map);

    @PostMapping("/add")
    public boolean add(@RequestBody FinanceAccount financeAccount);

    @PostMapping("/update")
    public boolean update(@RequestBody FinanceAccount financeAccount);

    @PostMapping("/deleteById/{accountId}")
    public boolean deleteById(@PathVariable("accountId") Integer accountId);

    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids);

}
