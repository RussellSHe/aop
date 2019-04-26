package aop;

import CaseDemo1.config.SpringConfig;
import CaseDemo1.dao.AccountDao;
import CaseDemo1.domain.Account;
import CaseDemo1.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ProjectName: aop
 * @Package: aop
 * @ClassName: ServiceTest
 * @Description:
 * @Author: zengyanbo
 * @CreateDate: 2019-04-25 15:22
 * @UpdateDate: 2019-04-25 15:22
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class ServiceTest {
    @Autowired
    private AccountService ac;

    @Test
    public void transferTest(){
        ac.transfer("wangwu","zhaoliu",5f);
    }

    @Test
    public void findAll(){
        List<Account> allAccount = ac.findAllAccount();
        allAccount.forEach(System.out::println);
    }
}
