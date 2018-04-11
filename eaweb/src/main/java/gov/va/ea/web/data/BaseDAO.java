package gov.va.ea.web.data;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseDAO {

	protected static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	protected static JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");

}
