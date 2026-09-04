package com.baidu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 图表数据 REST 控制器（服务提供者）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@RestController
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取月度收支分析数据
     */
    @GetMapping("/getMonthAnalysis")
    public Map<String, Object> getMonthAnalysis() {
        System.out.println("-------getMonthAnalysis-------");
        Map<String, Object> result = new HashMap<>();
        List<String> xAxis = new ArrayList<>();
        List<Double> incomeData = new ArrayList<>();
        List<Double> expenseData = new ArrayList<>();

        // 查询最近6个月的收支数据
        String sql = "SELECT DATE_FORMAT(create_time, '%Y-%m') AS month, " +
                     "SUM(CASE WHEN status = '收入' THEN amount ELSE 0 END) AS income, " +
                     "SUM(CASE WHEN status = '支出' THEN amount ELSE 0 END) AS expense " +
                     "FROM t_transaction " +
                     "GROUP BY DATE_FORMAT(create_time, '%Y-%m') " +
                     "ORDER BY month DESC LIMIT 6";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        // 反转顺序（从早到晚）
        Collections.reverse(rows);

        for (Map<String, Object> row : rows) {
            String month = (String) row.get("month");
            Object incomeObj = row.get("income");
            Object expenseObj = row.get("expense");
            Double income = incomeObj instanceof BigDecimal ? ((BigDecimal) incomeObj).doubleValue()
                          : incomeObj instanceof Number ? ((Number) incomeObj).doubleValue()
                          : 0.0;
            Double expense = expenseObj instanceof BigDecimal ? ((BigDecimal) expenseObj).doubleValue()
                           : expenseObj instanceof Number ? ((Number) expenseObj).doubleValue()
                           : 0.0;

            xAxis.add(month != null ? month : "");
            incomeData.add(income);
            expenseData.add(expense);
        }

        result.put("xAxis", xAxis);
        result.put("incomeData", incomeData);
        result.put("expenseData", expenseData);

        return result;
    }

    /**
     * 获取收支占比分析数据（按分类）
     * 注意：t_transaction.category_id 存储分类ID（INT），关联 t_category 获取分类名称
     */
    @GetMapping("/getCategoryAnalysis")
    public Map<String, Object> getCategoryAnalysis() {
        System.out.println("-------getCategoryAnalysis-------");
        Map<String, Object> result = new HashMap<>();
        List<String> names = new ArrayList<>();
        List<Map<String, Object>> data = new ArrayList<>();

        String sql = "SELECT c.category_name AS name, SUM(t.amount) AS value " +
                     "FROM t_transaction t " +
                     "LEFT JOIN t_category c ON t.category_id = c.category_id " +
                     "WHERE t.status = '支出' " +
                     "GROUP BY c.category_name " +
                     "ORDER BY value DESC";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> row : rows) {
            String name = (String) row.get("name");
            Object valueObj = row.get("value");
            Double value = valueObj instanceof BigDecimal ? ((BigDecimal) valueObj).doubleValue()
                         : valueObj instanceof Number ? ((Number) valueObj).doubleValue()
                         : 0.0;

            String label = name != null ? name : "其他";
            names.add(label);

            Map<String, Object> item = new HashMap<>();
            item.put("value", value);
            item.put("name", label);
            data.add(item);
        }

        result.put("names", names);
        result.put("data", data);

        return result;
    }

}
