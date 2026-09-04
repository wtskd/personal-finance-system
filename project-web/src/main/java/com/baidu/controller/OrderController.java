package com.baidu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-03
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    //跳转到订单列表页面
    @RequestMapping("/toOrderList")
    public String toOrderList() {
        return "order/list";
    }
}
