package com.baidu.service.impl;

import com.baidu.pojo.Cart;
import com.baidu.dao.AccountMapper;
import com.baidu.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  购物车 服务实现类
 * </p>
 *
 * @author xiaoye
 * @since 2026-06-15
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Cart> implements AccountService {

}
