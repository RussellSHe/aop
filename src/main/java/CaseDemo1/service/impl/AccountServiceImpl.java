package CaseDemo1.service.impl;

import CaseDemo1.dao.AccountDao;
import CaseDemo1.domain.Account;
import CaseDemo1.service.AccountService;
import CaseDemo1.utils.TransactionManager;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    public void setAc(AccountDao ac) {
        this.ac = ac;
    }

    public void setAccountDao(AccountDao ac) {
        this.ac = ac;
    }

    public List<Account> findAllAccount() {

        return  ac.findAllAccount();
    }

    public Account findAccountById(Integer id) {

      return ac.findAccountById(id);
    }

    public void saveAccount(Account acc) {

        ac.saveAccount(acc);
    }

    public void updateAccount(Account acc) {

       ac.saveAccount(acc);
    }

    public void deleteAccount(Integer id) {
       ac.deleteAccount(id);

    }

    @Override
    public void transfer(String src, String dst, Float money) {

            //执行操作
            Account srcAcconut = ac.findAccountByName(src);
            Account dstAcconut = ac.findAccountByName(dst);
            srcAcconut.setMoney(srcAcconut.getMoney()-money);
            dstAcconut.setMoney(dstAcconut.getMoney()+money);
            ac.updateAccount(srcAcconut);
            int i=1/0;
            ac.updateAccount(dstAcconut);


    }
}
