package movies.config;

import jdk.jfr.Name;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.util.ResourceUtils;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@Import(NetflixConfiguration.class)
public class HibernateConfiguration {

    @Inject
    DataSource dataSource;

    @Bean("global.conf.mainProperties")
    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ResourceUtils.getFile("classpath:./config.properties")));
        return properties;
    }

    @Bean("dataSource")
    public DataSource getMainDS(){
        Properties properties = new Properties();
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(Driver.class.getName());
        driverManagerDataSource.setUrl(resolveProperty(properties,"db.url"));
        driverManagerDataSource.setPassword("db.username");
        driverManagerDataSource.setUsername("db.password");
        return driverManagerDataSource;
    }

    private String resolveProperty(Properties properties, String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    @Bean
    @Name("beanFactory")
    public LocalSessionFactoryBean getSessionFactory(@Autowired DataSource ds) {
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
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        return new HibernateTransactionManager(sessionFactory);
    }
}
