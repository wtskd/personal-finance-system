package com.baidu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 交易记录实体（映射 t_transaction 表）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "transaction_id", type = IdType.AUTO)
    private Integer transactionId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 账户ID
     */
    private Integer accountId;

    /**
     * 交易时间
     */
    private Date createTime;

    /**
     * 状态（收入/支出）
     */
    private String status;

    /**
     * 分类ID（关联t_category.category_id）
     */
    private Integer categoryId;

    /**
     * 分类名称（非数据库字段，仅用于列表展示）
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 关联的账户对象（非数据库字段）
     */
    @TableField(exist = false)
    private FinanceAccount financeAccount;


}
