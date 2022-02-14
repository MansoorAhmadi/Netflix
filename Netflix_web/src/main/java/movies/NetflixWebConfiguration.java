package movies;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"movies.repositories", "movies.services"})
public class NetflixWebConfiguration {
    //stereotype access
}
