package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mapper.AccountMapper;
import com.itheima.pojo.Account;
import com.itheima.pojo.Result;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper,Account> implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Result Kk(String userAccount, Integer cost) {
        //1.获取这个账户还存在得金额 （余额）
        LambdaQueryWrapper<Account> wapper = new LambdaQueryWrapper<>();
        //设置查询得条件
        wapper.eq(Account::getUserAccount,userAccount);
        Account account = getOne(wapper);//查询一个数据

        if(account == null){
            return new Result(false,"该用户不存在",userAccount);
        }
        //2.余额 - cost >= 允许扣款
        if(account.getAmount() - cost < 0){
            return new Result(false,"余额不足...",userAccount);
        }
        //金额封装程一个对象  设置位扣减得金额
        account.setAmount(cost);
        //修改数据库得金额 即可
        int i =  accountMapper.costMoney(account);

        if(i>0){
            return new Result(true,"扣款成功",userAccount);
        }
        return new Result(false,"扣款失败",userAccount);
    }
}
