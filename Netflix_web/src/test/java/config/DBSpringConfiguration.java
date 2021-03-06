package config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

public class DBSpringConfiguration {

    @Bean("services.data.myFirstQueryAsBeanConfig")
    public String getQuery(){
        return "select * from Questions";
    }


    @Bean("global.conf.mainProperties")
    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ResourceUtils.getFile("src/test/resources/config.properties")));
//        "classpath:./config.properties"
        return properties;
    }

    @Bean("services.data.mainDS")
    public DataSource getMainDS(@Autowired @Qualifier("global.conf.mainProperties") Properties properties){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(Driver.class.getName());
        driverManagerDataSource.setUrl(resolveProperty(properties,"db.url"));
        driverManagerDataSource.setPassword("db.username");
        driverManagerDataSource.setUsername("db.password");
        return driverManagerDataSource;

    }

    private String resolveProperty(Properties properties, String propertyKey)  {
        return properties.getProperty(propertyKey);
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory(@Autowired DataSource ds){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(ds);
        factoryBean.setPackagesToScan("movies.datamodel");
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        factoryBean.setHibernateProperties(hibernateProperties);
        return factoryBean;
    }

    @Bean
    public TransactionManager getTransactionManager(@Autowired SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return (TransactionManager) transactionManager;
    }

}
