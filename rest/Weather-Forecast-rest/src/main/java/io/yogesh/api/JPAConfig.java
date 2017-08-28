package io.yogesh.api;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value="classpath:application.properties")
public class JPAConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean emf(){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setDataSource(getDataSource());
		emf.setJpaProperties(getJPAProperties());
		emf.setPackagesToScan("io.yogesh.api.entity");
		
		return emf;
	}
	
	public DataSource getDataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl(env.getProperty("db.url"));
		ds.setUsername(env.getProperty("db.user", "root"));
		ds.setPassword(env.getProperty("db.password","root"));
		
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager ptm(EntityManagerFactory em){
		return new JpaTransactionManager(em);
	}
	
	private Properties getJPAProperties(){
		Properties ps = new Properties();
		ps.setProperty("hibernate.show_sql", env.getProperty("hibernate-show-SQL"));
		ps.setProperty("hibernate.format_sql", env.getProperty("hibernate-format-SQL"));
		ps.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		ps.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate-hbm2ddl"));
		
		return ps;
	}
	

}