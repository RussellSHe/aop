package CaseDemo1.service;

import CaseDemo1.domain.Account;

import java.util.List;

/**
 * @ProjectName: Test1
 * @Package: CaseDemo1.Service
 * @ClassName: AccountService
 * @Description:
 * @Author: zengyanbo
 * @CreateDate: 2019-04-23 17:29
 * @UpdateDate: 2019-04-23 17:29
 * @Version: 1.0
 */

public interface AccountService {

    List<Account> findAllAccount();

    Account findAccountById(Integer id);

    void saveAccount(Account ac);

    void updateAccount(Account ac);

    void deleteAccount(Integer id);
    void transfer(String src,String dst,Float money);

}
