package CaseDemo1.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


/**
 * User: Russell
 * Date: 2019-04-24
 * Time: 23:39
 */
//@Configuration
//@ComponentScan("CaseDemo1")
public class JdbcConfig {
    @Value("${driver}")
    private String driver;
    @Value("${jdbcUrl}")
    private String url;
    @Value("${user}")
    private String name;
    @Value("${password}")
    private String pwd;
    @Bean(name = "runner")
    @Scope(value="prototype")  //多例的
    public QueryRunner createQueryRunner(DataSource source) {
        return new QueryRunner(source);
    }

    @Bean(name = "source")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource pool = new ComboPooledDataSource();
            pool.setDriverClass(driver);
            pool.setJdbcUrl(url);
            pool.setUser(name);
            pool.setPassword(pwd);
            return pool;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
