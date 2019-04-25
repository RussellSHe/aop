package CaseDemo1;

import CaseDemo1.config.JdbcConfig;
import CaseDemo1.config.SpringConfig;
import CaseDemo1.domain.Account;
import CaseDemo1.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * User: Russell
 * Date: 2019-04-23
 * Time: 22:54
 */
public class Client {

   public static void main(String[] args) {
      //ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
      AccountService ac = (AccountService) context.getBean("accountService");
      List<Account> allAccount = ac.findAllAccount();
      allAccount.forEach(System.out::println);

      /*Account accountById = ac.findAccountById(3);
      System.out.println(accountById);*/
      /*Account account = new Account();
      account.setName("qianba");
      account.setMoney(10.22f);
      ac.saveAccount(account);*/
      /*Account account = new Account();
      account.setId(4);
      account.setName("zhaoliu");
      account.setMoney(6.85f);
      ac.updateAccount(account);
      ac.deleteAccount(3);*/

   }



}