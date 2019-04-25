package CaseDemo1.dao;

import CaseDemo1.domain.Account;

import java.util.List;

/**
 * @ProjectName: Test1
 * @Package: CaseDemo1.dao
 * @ClassName: AccountDao
 * @Description:
 * @Author: zengyanbo
 * @CreateDate: 2019-04-23 17:43
 * @UpdateDate: 2019-04-23 17:43
 * @Version: 1.0
 */

public interface AccountDao {
    List<Account> findAllAccount();

    Account findAccountById(Integer id);

    void saveAccount(Account ac);

    void updateAccount(Account ac);

    void deleteAccount(Integer id);

    Account findAccountByName(String name);
}
