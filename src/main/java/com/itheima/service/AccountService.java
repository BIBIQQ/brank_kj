package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.pojo.Account;
import com.itheima.pojo.Result;

public interface AccountService extends IService<Account> {
    Result Kk(String userAccount, Integer cost);
}
