package com.baidu.service;

import com.baidu.pojo.Product;
import com.baidu.utils.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-03
 */
public interface ProductService extends IService<Product> {
    public List<Product> getProductByPage2(Integer currentPage,Integer pageSize);
    public PageResult<Product> getProductByPage(String pname,Integer currentPage,Integer pageSize);
}
