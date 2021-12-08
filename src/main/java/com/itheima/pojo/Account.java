package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_account")
public class Account {
    @TableId
    private Integer id;
    @TableField("userAccount")
    private String userAccount;
    @TableField("amount")
    private Integer amount;
}
