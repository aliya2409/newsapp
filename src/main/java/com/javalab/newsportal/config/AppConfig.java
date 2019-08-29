package com.javalab.newsportal.config;

import com.javalab.newsportal.dao.CommentDAO;
import com.javalab.newsportal.dao.NewsDAO;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.javalab.newsportal")
public class AppConfig implements WebMvcConfigurer {

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.javalab.newsportal.model");
        sessionFactory.setMappingResources("hibernate.orm.xml");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        ResourceBundle rb = getBundle("database");
        dataSource.setDriverClassName(rb.getString("oracle.driver"));
        dataSource.setUrl(rb.getString("oracle.url"));
        dataSource.setUsername(rb.getString("oracle.user"));
        dataSource.setPassword(rb.getString("oracle.password"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        ResourceBundle rb = getBundle("database");
        hibernateProperties.setProperty(
                "hibernate.dialect", rb.getString("oracle.dialect"));

        return hibernateProperties;
    }

    @Bean
    public News news() {
        return new News();
    }

    @Bean
    public Comment comment() {
        return new Comment();
    }

    @Bean
    public NewsDAO newsDAO() {
        return new NewsDAO(sessionFactory().getObject(), News.class);
    }

    @Bean
    public CommentDAO commentDAO() {
        return new CommentDAO(sessionFactory().getObject(), Comment.class);
    }
}
