package CaseDemo1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * User: Russell
 * Date: 2019-04-25
 * Time: 0:25
 */
@ComponentScan("CaseDemo1")
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfig {
}
