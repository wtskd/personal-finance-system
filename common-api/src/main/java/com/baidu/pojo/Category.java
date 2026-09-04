package com.baidu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 分类名
     */
    private String categoryName;

    /**
     * 预算金额/参考
     */
    private BigDecimal amount;

    /**
     * 图标路径
     */
    private String icon;

    /**
     * 描述
     */
    private String description;

    /**
     * 类型(1收入/2支出)
     */
    private Integer typeId;


}
