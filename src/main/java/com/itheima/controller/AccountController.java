package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 账户得控制层
 *  接收用户请求 以及返回用户得数据  调用service
 *
 *  @RestController  -- > 作用 让AccountController 编程一个可以处理请求得Controller
 *
 */
@RestController
@RequestMapping("/bank/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    /**
     * 扣款方法得定义
     * 1.请求参数是什么？  String userAccount,Integer cost
     * 2.请求返回值是什么？   Result
     * 幂等性 ： 统一时间多次请求，只执行一次  金融，银行
     * 幂等key
     */

    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("/cost.do")
    public Result KouKuan(String userAccount, Integer cost, String miKey,HttpServletRequest request){
        //保证设置值和取值是同时执行呢？
        /*HttpSession session = request.getSession();
            Integer userAccountNum = (Integer) session.getAttribute("userAccountNum");
            //如果保证相同的请求只能执行一次呢？
            if(userAccountNum > 1 || userAccountNum !=null ){
                return null;
            }
            session.setAttribute("userAccountNum",2);*/
        //业务逻辑代码  给这个key的value自行一
        //increment 自增后的值
        //问题，如果断时间多次转账    可以设置时效
        //不完整，短时间可以多次转账。
        Long increment = redisTemplate.opsForValue().increment(miKey);
        if(increment.longValue()>1){
            return null;
        }
        redisTemplate.expire(userAccount,1,TimeUnit.MINUTES);

        //调用service。结束
        if(userAccount == "" || cost == null || cost <=0){
            return new Result(false,"参数异常，请重新输入",userAccount);
        }
        return accountService.Kk(userAccount,cost);
    }
}
