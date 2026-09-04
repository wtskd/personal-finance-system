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
 * 财务账户实体（映射 t_account 表）
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_account")
public class FinanceAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "account_id", type = IdType.AUTO)
    private Integer accountId;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 账户余额
     */
    private BigDecimal balance;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态（如：正常/禁用）
     */
    private String status;

    /**
     * 预算类型ID（关联 t_budget_type）
     */
    private Integer typeId;

    /**
     * 关联的预算类型对象（非数据库字段）
     */
    @TableField(exist = false)
    private BudgetType budgetType;

}
