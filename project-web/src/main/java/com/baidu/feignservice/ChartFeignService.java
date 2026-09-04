package com.baidu.feignservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
@FeignClient(value = "project-service", path = "/chart")
public interface ChartFeignService {

    @GetMapping("/getMonthAnalysis")
    public Map<String, Object> getMonthAnalysis();

    @GetMapping("/getCategoryAnalysis")
    public Map<String, Object> getCategoryAnalysis();

}
