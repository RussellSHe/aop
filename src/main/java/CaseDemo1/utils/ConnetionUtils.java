package CaseDemo1.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ProjectName: aop
 * @Package: CaseDemo1.utils
 * @ClassName: ConnetionUtils
 * @Description:
 * @Author: zengyanbo
 * @CreateDate: 2019-04-25 16:21
 * @UpdateDate: 2019-04-25 16:21
 * @Version: 1.0
 */
@Component("connectionUtils")
public class ConnetionUtils {
    //使用Threadlocal对象把当前线程和Connection绑定，使一个线程中只有一个控制事务的对象
    private ThreadLocal<Connection> tl=new ThreadLocal<>();
    @Autowired
    private DataSource ds;

    public Connection getThreadConnection(){
        //先从当前线程上获取
        Connection connection = tl.get();
        //没有从数据源上获取 并存入ThreadLocal中
        try {
            if(connection==null){
                 connection = ds.getConnection();
                 tl.set(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;


    }

    /**
     * 把线程和连接解绑
     */
    public void removeConnection(){
        tl.remove();
    }
}
