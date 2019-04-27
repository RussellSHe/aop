package CaseDemo1.factory;

import CaseDemo1.domain.Account;
import CaseDemo1.service.AccountService;
import CaseDemo1.utils.TransactionManager;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;


/**
 * @ProjectName: aop
 * @Package: CaseDemo1.factory
 * @ClassName: BeanFactory
 * @Description:用于提供Service代理类的工厂
 * @Author: zengyanbo
 * @CreateDate: 2019-04-26 13:55
 * @UpdateDate: 2019-04-26 13:55
 * @Version: 1.0
 */

public class BeanFactory {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionManager txmanager;

    /*public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }*/

    public final void setTxmanager(TransactionManager txmanager) {
        this.txmanager = txmanager;
    }

    @Bean("proxyAccountService")
    public  AccountService getProxyAccountService(){
        AccountService proxyInstance = (AccountService)Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;
                        try {
                            //开启事务
                            txmanager.beginTransaction();
                            System.out.println("开启事务...");
                            //执行操作
                            result = method.invoke(accountService, args);
                            //提交事务
                            txmanager.commit();
                            //返回结果
                            return result;
                        } catch (Exception e) {
                            //回滚操作
                            txmanager.rollBack();
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        } finally {
                            //关闭事务 释放连接
                            txmanager.closeTransaction();
                        }
                    }
                });
        return  proxyInstance;

    }
}
