package com.baidu.feignservice;

import com.baidu.pojo.Product;
import com.baidu.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(value = "project-service",path = "/product")
public interface ProductFeignService {
    @GetMapping("/count")
    public long count();

    @GetMapping("/getProductByPage2/{currentPage}/{pageSize}")
    public List<Product> getProductByPage2(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize);

    @PostMapping("updateProduct")
    public boolean updateProduct(@RequestBody Product product);

    @GetMapping("/getProductById/{pid}")
    public Product getProductById(@PathVariable("pid") Integer pid);

    @PostMapping("addProduct")
    public boolean addProduct(@RequestBody Product product);

    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> List);

    @PostMapping("/deleteProductById/{id}")
    public boolean deleteProductById(@PathVariable("id") Integer id);

    @PostMapping("/getProductByPage")
    public PageResult<Product> getProductByPage(@RequestBody Map<String,Object> map);
}
