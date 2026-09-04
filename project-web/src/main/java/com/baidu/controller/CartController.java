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
@RequestMapping("/cart")
public class CartController {

    //跳转到购物车列表页面
    @RequestMapping("/toCartList")
    public String toCartList() {
        return "cart/list";
    }
}
