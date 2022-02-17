package movies.config;

import movies.services.impl.AddressJPADAO;
import movies.services.impl.MovieJPADAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.test.context.ContextConfiguration;

@Configuration
@ComponentScan(basePackages =
        {
                "movies.services.impl",
                "movies.services.api",
                "movies.repositories",
                "movies.services"
        })

@ContextConfiguration("classpath*: applicationContext.xml")
public class NetflixConfiguration {

    @Bean("movieDAO")
    public MovieJPADAO getMovieJPADAO(){
        return new MovieJPADAO();
    }

    @Bean("addressDAO")
    public AddressJPADAO getAddressJPADAO(){
        return new AddressJPADAO();
    }


}
