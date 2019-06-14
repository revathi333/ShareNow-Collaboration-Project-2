package com.coll.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.coll.Model.*;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.coll")
public class DBConfig 
{

	@Bean(name="dataSource")
	DataSource getDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
		dataSource.setUsername("revathi");
		dataSource.setPassword("revathi");
		
		System.out.println("===========================DataSource Object Created===========================");
		
		return dataSource;		
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	SessionFactory getsessionFactory(DataSource dataSource)
	{  
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.hbm2ddl.auto","update");
		hibernateProp.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");

		LocalSessionFactoryBuilder factory = new LocalSessionFactoryBuilder(dataSource);
		factory.addProperties(hibernateProp);
		factory.addAnnotatedClass(Blog.class);
		factory.addAnnotatedClass(BlogComment.class);
		factory.addAnnotatedClass(UserInfo.class);
		factory.addAnnotatedClass(Job.class);
		factory.addAnnotatedClass(Friend.class);
		factory.addAnnotatedClass(UserLikedBlog.class);
		factory.addAnnotatedClass(ProfilePicture.class);

		SessionFactory sessionFactory = factory.buildSessionFactory();
		
		System.out.println("===========================SessionFactory Object Created===========================");
		
		return sessionFactory;		
	}

	@Autowired
	@Bean(name="txManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("===========================HibernateTransactionManager Object Created===========================");

		return new HibernateTransactionManager(sessionFactory);
	}
	
}
