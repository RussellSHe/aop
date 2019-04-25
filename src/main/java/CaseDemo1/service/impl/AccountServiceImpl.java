package CaseDemo1.service.impl;

import CaseDemo1.dao.AccountDao;
import CaseDemo1.domain.Account;
import CaseDemo1.service.AccountService;
import CaseDemo1.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: Test1
 * @Package: CaseDemo1.service.impl
 * @ClassName: AccountServiceImpl
 * @Description: 业务层  作事务处理
 * @Author: zengyanbo
 * @CreateDate: 2019-04-23 17:30
 * @UpdateDate: 2019-04-23 17:30
 * @Version: 1.0
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao ac;
    @Autowired
    public TransactionManager txmanager;

    public void setAccountDao(AccountDao ac) {
        this.ac = ac;
    }

    public List<Account> findAllAccount() {

        try {
            //开启事务
            txmanager.beginTransaction();
            //执行操作
            List<Account> allAccount = ac.findAllAccount();
            //提交事务
            txmanager.commit();
            //返回结果
            return allAccount;
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

    public Account findAccountById(Integer id) {

        try {
            //开启事务
            txmanager.beginTransaction();
            //执行操作
            Account account = ac.findAccountById(id);
            //提交事务
            txmanager.commit();
            //返回结果
            return account;
        } catch (Exception e) {
            //回滚操作
            txmanager.rollBack();
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //关闭事务
            txmanager.closeTransaction();
        }
    }

    public void saveAccount(Account acc) {

        try {
            //开启事务
            txmanager.beginTransaction();
            //执行操作
            ac.saveAccount(acc);
            //提交事务
            txmanager.commit();
            //返回结果

        } catch (Exception e) {
            //回滚操作
            txmanager.rollBack();
            e.printStackTrace();
        } finally {
            //关闭事务
            txmanager.closeTransaction();
        }
    }

    public void updateAccount(Account acc) {

        try {
            //开启事务
            txmanager.beginTransaction();
            //执行操作
            ac.updateAccount(acc);
            //提交事务
            txmanager.commit();
            //返回结果

        } catch (Exception e) {
            //回滚操作
            txmanager.rollBack();
            e.printStackTrace();
        } finally {
            //关闭事务
            txmanager.closeTransaction();
        }
    }

    public void deleteAccount(Integer id) {
        try {
            //开启事务
            txmanager.beginTransaction();
            //执行操作
            ac.deleteAccount(id);
            //提交事务
            txmanager.commit();
            //返回结果

        } catch (Exception e) {
            //回滚操作
            txmanager.rollBack();
            e.printStackTrace();
        } finally {
            //关闭事务
            txmanager.closeTransaction();
        }

    }

    @Override
    public void transfer(String src, String dst, Float money) {
        try {
            //开启事务
            txmanager.beginTransaction();
            //执行操作
            Account srcAcconut = ac.findAccountByName(src);
            Account dstAcconut = ac.findAccountByName(dst);
            srcAcconut.setMoney(srcAcconut.getMoney()-money);
            dstAcconut.setMoney(dstAcconut.getMoney()+money);
            ac.updateAccount(srcAcconut);
            ac.updateAccount(dstAcconut);
            //提交事务
            txmanager.commit();
            //返回结果

        } catch (Exception e) {
            //回滚操作
            txmanager.rollBack();
            e.printStackTrace();
        } finally {
            //关闭事务
            txmanager.closeTransaction();
        }


    }
}
