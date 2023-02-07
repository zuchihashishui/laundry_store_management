package laundry.com.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class MyBatisConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("database.driver"));
		hikariConfig.setJdbcUrl(env.getProperty("database.url"));
		hikariConfig.setUsername(env.getProperty("database.username"));
		hikariConfig.setPassword(env.getProperty("database.password"));
		hikariConfig.addDataSourceProperty("characterEncoding",env.getProperty("database.CharacterEncoding"));
		hikariConfig.addDataSourceProperty("useUnicode", env.getProperty("database.UseUnicode"));
		hikariConfig.addDataSourceProperty("useServerPrepStmts", true);
		hikariConfig.addDataSourceProperty("cachePrepStmts", true);
		hikariConfig.setAutoCommit(true);

		return new HikariDataSource(hikariConfig);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(resolver.getResources("classpath*:mybatis/*.xml"));

		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setCallSettersOnNulls(true);
		configuration.setReturnInstanceForEmptyRow(true);
		factoryBean.setConfiguration(configuration);
		
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}
