package movies;

import movies.datamodel.Movie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NetflixWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetflixWebApplication.class);
    }
    //Checking dependency connection
//    Movie movie = new Movie();
}
