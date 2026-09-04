package com.baidu.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

//用于查询，特别是分页查询
@Data
public class PageResult<T> implements Serializable {
    private Integer code;  //处理是否成功，0代表成功，其他值代表失败
    private String msg;     // 处理完成后，带到前台的消息
    private Integer count;   // 用来前台计算总共有多少页数据
    private List<T> data;   // 当前page数据页中包含的数据集合
}
