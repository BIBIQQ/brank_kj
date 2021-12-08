package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.Account;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AccountMapper extends BaseMapper<Account> {
    @Update("update  user_account  set amount = amount - #{amount} where userAccount = #{userAccount}")
    int costMoney(Account account);
}
