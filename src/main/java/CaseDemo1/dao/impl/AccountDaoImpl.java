package CaseDemo1.dao.impl;

import CaseDemo1.dao.AccountDao;
import CaseDemo1.domain.Account;
import CaseDemo1.utils.ConnetionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: Test1
 * @Package: CaseDemo1.dao.impl
 * @ClassName: AccountDaoImpl
 * @Description:
 * @Author: zengyanbo
 * @CreateDate: 2019-04-23 17:45
 * @UpdateDate: 2019-04-23 17:45
 * @Version: 1.0
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
    @Autowired
    QueryRunner runner;
    @Autowired
    private ConnetionUtils connetionUtils;

    @Override
    public Account findAccountByName(String name) {
        try {
            List<Account> list=runner.query(connetionUtils.getThreadConnection(),"select * from account where name=?",new BeanListHandler<Account>(Account.class),name);
            if(list==null||list.size()==0){
                return null;
            }
            if(list.size()>1){
                throw new RuntimeException("账户不唯一，数据错误");
            }
            return list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public List<Account> findAllAccount() {
        try {
             return runner.query(connetionUtils.getThreadConnection(),"select * from account",new BeanListHandler<Account>(Account.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public Account findAccountById(Integer id) {
        try {
            return runner.query(connetionUtils.getThreadConnection(),"select * from account where id=?",new BeanHandler<Account>(Account.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void saveAccount(Account ac) {
        try {
             runner.update(connetionUtils.getThreadConnection(),"insert into account(name,money) values(?,?) ",ac.getName(),ac.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(Account ac) {
        try {
            runner.update(connetionUtils.getThreadConnection(),"update account set name=?,money=?  where id=? ",ac.getName(),ac.getMoney(),ac.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(Integer id) {
        try {
            runner.update(connetionUtils.getThreadConnection(),"delete from account where id=? ",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
