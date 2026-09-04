package com.baidu.controller;


import com.baidu.feignservice.ChartFeignService;
import com.baidu.feignservice.ProductFeignService;
import com.baidu.pojo.Product;
import com.baidu.utils.PageResult;
import com.baidu.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-03
 */
@Controller
@RequestMapping("/prod")
public class ProductController {

    @Autowired
    private ProductFeignService productFeignService;

    @Autowired
    private ChartFeignService chartFeignService;

    @GetMapping("/getProdById/{id}")
    public String getProdById(@PathVariable("id") Integer id,HttpServletRequest request){
        System.out.println("-----getProdById-----id:"+id);
        Product product = productFeignService.getProductById(id);
        request.setAttribute("product",product);
        return "prod/info";
    }

    @RequestMapping("/index.html")
    public String index(@RequestParam(defaultValue = "1") Integer currentPage,
                        @RequestParam(defaultValue = "6") Integer pageSize,HttpServletRequest request){
        System.out.println("-----index-----");
        System.out.println("currentPage:"+currentPage);
        System.out.println("pageSize:"+pageSize);

        List<Product> list = productFeignService.getProductByPage2(currentPage, pageSize);
        long total = productFeignService.count();
        request.setAttribute("pages",currentPage);
        request.setAttribute("totalCount",total);
        request.setAttribute("list",list);

        return "index";
    }

    @PostMapping("/doEdit")
    @ResponseBody
    public R doEdit(Product product){
        System.out.println("-----doEdit-----");
        System.out.println("product:"+product);
        boolean flag = productFeignService.updateProduct(product);
        if (flag){
            return R.right();
        }else {
            return R.error();
        }
    }

    @RequestMapping("/toEdit/{pid}")
    public String toEdit(@PathVariable("pid") Integer pid, HttpServletRequest request){
        System.out.println("-----toEdit-----pid:"+pid);
        Product product = productFeignService.getProductById(pid);
        request.setAttribute("product",product);
        return "prod/edit";
    }

    @PostMapping("/doAdd")
    @ResponseBody
    public R doAdd(Product product){
        System.out.println("-----doAdd-----");
        System.out.println("product:"+product);
        boolean flag = productFeignService.addProduct(product);
        if (flag){
            return R.right();
        }else {
            return R.error();
        }
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        System.out.println("-----toAdd-----");
        return "prod/add";
    }

    @PostMapping("/batchDel")
    @ResponseBody
    public R batchDel(@RequestParam(value = "prodIds[]") Integer prodIds[]){
        System.out.println("------batchDel-----");
        List<Integer> list =new ArrayList<>();
        for (Integer id: prodIds){
            System.out.println("id:"+ id);
            list.add(id);
        }
        boolean flag = productFeignService.deleteByIds(list);
        if (flag){
            return R.right();
        }else {
            return R.error();
        }
    }

    @PostMapping("/doDel/{pid}")
    @ResponseBody
    public R deleteById(@PathVariable("pid") Integer pid) {
        System.out.println("------deleteById-------pid:" + pid);

        boolean result = productFeignService.deleteProductById(pid);

        if (result) {
            return R.right();
        } else {
            return R.error();
        }
    }

    @PostMapping("getPage")
    @ResponseBody
    public PageResult<Product> getPage(@RequestParam(defaultValue = "") String pname,
                                       @RequestParam(defaultValue = "1")Integer page,
                                       @RequestParam(defaultValue = "5")Integer limit){
        System.out.println("-----getPage-----");
        System.out.println("pname:"+pname);
        System.out.println("page:"+page);
        System.out.println("limit:"+limit);

        if ("".equals(pname)){
            pname=null;
        }

        Map<String,Object> map=new HashMap<>();
        map.put("pname",pname);
        map.put("currentPage",page);
        map.put("pageSize",limit);

        PageResult<Product> pageResult=productFeignService.getProductByPage(map);

        return pageResult;
    }


    @RequestMapping("/toList")
    public String toList(){
        return "prod/list";
    }

    @RequestMapping("/toView1")
    public String toView1(HttpServletRequest request){
        System.out.println("-------toView1（真实数据）-------");
        try {
            Map<String, Object> chartData = chartFeignService.getMonthAnalysis();
            List<?> xAxis = (List<?>) chartData.get("xAxis");
            List<?> incomeData = (List<?>) chartData.get("incomeData");
            List<?> expenseData = (List<?>) chartData.get("expenseData");

            ObjectMapper mapper = new ObjectMapper();
            request.setAttribute("echartTitle", "财务月度分析");
            request.setAttribute("echartXAxis", mapper.writeValueAsString(xAxis));
            request.setAttribute("echartIncome", mapper.writeValueAsString(incomeData));
            request.setAttribute("echartExpense", mapper.writeValueAsString(expenseData));
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("echartTitle", "财务月度分析");
            request.setAttribute("echartXAxis", "[]");
            request.setAttribute("echartIncome", "[]");
            request.setAttribute("echartExpense", "[]");
        }
        return "prod/view1";
    }

    @RequestMapping("/toView2")
    public String toView2(HttpServletRequest request){
        System.out.println("-------toView2（真实数据）-------");
        try {
            Map<String, Object> chartData = chartFeignService.getCategoryAnalysis();
            List<?> names = (List<?>) chartData.get("names");
            List<?> data = (List<?>) chartData.get("data");

            ObjectMapper mapper = new ObjectMapper();
            request.setAttribute("echartTitle", "财务占比分析");
            request.setAttribute("echartName", mapper.writeValueAsString(names));
            request.setAttribute("echartData", mapper.writeValueAsString(data));
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("echartTitle", "财务占比分析");
            request.setAttribute("echartName", "[]");
            request.setAttribute("echartData", "[]");
        }
        return "prod/view2";
    }

}
