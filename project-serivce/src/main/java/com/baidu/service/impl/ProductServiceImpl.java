package com.baidu.service.impl;

import com.baidu.pojo.Product;
import com.baidu.dao.ProductMapper;
import com.baidu.service.ProductService;
import com.baidu.utils.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-03
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Product> getProductByPage2(Integer currentPage,Integer pageSize){
        Page<Product> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        IPage<Product> iPage = productMapper.selectPage(page, null);
        List<Product> list = iPage.getRecords();
        return list;
    }

    public PageResult<Product> getProductByPage(String pname, Integer currentPage, Integer pageSize) {
        Page<Product> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        if (pname != null){
            queryWrapper.like("pname",pname);
        }

        IPage<Product> iPage = productMapper.selectPage(page, queryWrapper);
        List<Product> list = iPage.getRecords();

        Long total =productMapper.selectCount(queryWrapper);
        int sum=total.intValue();

        PageResult<Product> result = new PageResult<>();
        result.setCode(0);
        result.setMsg("分页查询成功");
        result.setData(list);
        result.setCount(sum);

        return result;
    }

}
