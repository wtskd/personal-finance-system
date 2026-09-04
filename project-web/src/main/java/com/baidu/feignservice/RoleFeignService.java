package com.baidu.feignservice;

import com.baidu.pojo.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 角色 Feign 远程调用接口
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-03
 */
@FeignClient(value = "project-service", path = "/role")
public interface RoleFeignService {

    @GetMapping("/getAllRole")
    public List<Role> getAllRole();

    @GetMapping("/getById/{rid}")
    public Role getById(@PathVariable("rid") Integer rid);

    @PostMapping("/add")
    public boolean add(@RequestBody Role role);

    @PostMapping("/update")
    public boolean update(@RequestBody Role role);

    @PostMapping("/deleteById/{rid}")
    public boolean deleteById(@PathVariable("rid") Integer rid);

    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> ids);

}
