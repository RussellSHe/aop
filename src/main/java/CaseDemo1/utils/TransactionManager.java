package CaseDemo1.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * User: Russell
 * Date: 2019-04-25
 * Time: 22:58
 */
@Component("txManager")
public class TransactionManager {
    @Autowired
    ConnetionUtils connetionUtils;

    public void setConnetionUtils(ConnetionUtils connetionUtils) {
        this.connetionUtils = connetionUtils;
    }

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
}
