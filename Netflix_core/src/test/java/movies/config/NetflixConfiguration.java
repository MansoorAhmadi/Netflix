package movies.config;

import jdk.jfr.Name;
import movies.services.impl.MovieJPADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.ResourceUtils;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages =
        {
                "movies.services.impl",
                "movies.services.api",
                "movies.repositories",
                "movies.services"})

@ContextConfiguration("classpath*: applicationContext.xml")
public class NetflixConfiguration {

    @Inject
    DataSource dataSource;

    @Bean("movieDAO")
    public MovieJPADAO getMovieJPADAO(){
        return new MovieJPADAO();
    }

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
    @Name("factory")
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

}
