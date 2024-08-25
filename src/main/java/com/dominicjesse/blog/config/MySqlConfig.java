package com.dominicjesse.blog.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages="com.dominicjesse.blog.mysql.repository",
		transactionManagerRef = "mysqlTransactionManager")
@EnableTransactionManagement
public class MySqlConfig {
	
	@Value("${spring.mysql.jdpc-url}")
	private String mysqlJdpcUrl;

	@Bean(name="mysql")
	@ConfigurationProperties(prefix = "spring.mysql")
	public DataSource dataSource() {
		return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver").url(mysqlJdpcUrl).build();
	}
	
	@Bean(name="mysqlTransactionManager")
	public JpaTransactionManager mysqlTransactionManager(LocalContainerEntityManagerFactoryBean entityFactory) {
		return new JpaTransactionManager(entityFactory.getObject());
	}
	
}
