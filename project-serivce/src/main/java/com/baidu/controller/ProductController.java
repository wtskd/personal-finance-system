package com.baidu.controller;

import com.baidu.pojo.Product;
import com.baidu.service.ProductService;
import com.baidu.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/count")
    public long count() {
        System.out.println("-----count-----");
        long count = productService.count();
        return count;
    }

    @GetMapping("/getProductByPage2/{currentPage}/{pageSize}")
    public List<Product> getProductByPage2(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        System.out.println("-----getProductByPage2-----currentPage:"+currentPage+" pageSize:"+pageSize);
        List<Product> list = productService.getProductByPage2(currentPage, pageSize);
        return list;
    }

    @PostMapping("updateProduct")
    public boolean updateProduct(@RequestBody Product product) {
        System.out.println("-----updateProduct-----");
        System.out.println("product:"+product);
        boolean flag = productService.updateById(product);
        return flag;
    }

    @GetMapping("/getProductById/{pid}")
    public Product getProductById(@PathVariable("pid") Integer pid) {
        System.out.println("-----getProductById-----pid:"+pid);
        Product product = productService.getById(pid);
        return product;
    }

    @PostMapping("addProduct")
    public boolean addProduct(@RequestBody Product product) {
        System.out.println("-----addProduct-----");
        System.out.println("product:" + product);
        boolean flag = productService.save(product);
        return flag;
    }

    @PostMapping("/deleteByIds")
    public boolean deleteByIds(@RequestBody List<Integer> List) {
        System.out.println("-----deleteByIds------");
        for (Integer id : List) {
            System.out.println("id:" + id);
        }
        boolean flag = productService.removeByIds(List);

        return flag;
    }

    @PostMapping("/deleteProductById/{id}")
    public boolean deleteProductById(@PathVariable("id") Integer id) {
        System.out.println("-----deleteProductById------id:" + id);

        boolean flag = productService.removeById(id);

        return flag;
    }
    @PostMapping("/getProductByPage")
    public PageResult<Product> getProductByPage(@RequestBody Map<String,Object>map){
        System.out.println("-----getProductByPage-----");
        String pname= (String) map.get("pname");
        Integer currentPage= (Integer) map.get("currentPage");
        Integer pageSize= (Integer) map.get("pageSize");

        System.out.println("pname:"+pname+" currentPage:"+currentPage+" pageSize:"+pageSize);

        PageResult<Product> result=productService.getProductByPage(pname,currentPage,pageSize);
        return result;
    }
}
