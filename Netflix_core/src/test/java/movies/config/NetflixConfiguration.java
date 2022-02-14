package movies.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.util.ResourceUtils;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"movies.services", "movies.repositories"})
public class NetflixConfiguration {

    @Inject
    DataSource dataSource;

    @Bean("mainProperties")
    public Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(ResourceUtils.getFile("classpath:./config.properties")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Bean("dataSource")
    public DataSource getMainDS() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(Driver.class.getName());
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/Netflix");
        driverManagerDataSource.setPassword("postgres");
        driverManagerDataSource.setUsername("jasmine");
        return driverManagerDataSource;
    }

    private String resolveProperty(Properties properties, String propertyKey) {
        return properties.getProperty(propertyKey);

    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(this.dataSource);
        factoryBean.setPackagesToScan("movies.datamodel");

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        factoryBean.setHibernateProperties(hibernateProperties);

        return factoryBean;
    }
}
