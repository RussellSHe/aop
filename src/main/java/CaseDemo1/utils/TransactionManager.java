package CaseDemo1.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * User: Russell
 * Date: 2019-04-25
 * Time: 22:58
 */
@Component("txManager")
@Aspect
public class TransactionManager {
    @Autowired
   private ConnetionUtils connetionUtils;
    //@Pointcut("execution(* CaseDemo1.service.impl.*.*(..))")
   /* public void setConnetionUtils(ConnetionUtils connetionUtils) {

        this.connetionUtils = connetionUtils;
    }*/

    public void beginTransaction(){
        try {
            connetionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void commit(){
        try {
            connetionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }public void rollBack(){
        try {
            connetionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }public void closeTransaction(){
        try {
            connetionUtils.getThreadConnection().close();
            connetionUtils.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Around("execution(* CaseDemo1.service.impl.*.*(..))")
    public Object aroundAdice(ProceedingJoinPoint pjp){
            Object result=null;
        try {
            Object[] args = pjp.getArgs();
            beginTransaction();
            System.out.println("方法签名:"+pjp.getSignature());
            result=pjp.proceed(args);
            commit();
            return result;
        } catch (Throwable throwable) {
            rollBack();
            throwable.printStackTrace();
        } finally {
            closeTransaction();
        }
        return result;
    }
}
