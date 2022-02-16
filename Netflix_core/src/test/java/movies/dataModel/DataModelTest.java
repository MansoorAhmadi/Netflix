package movies.dataModel;

import movies.config.NetflixConfiguration;
import movies.datamodel.Movie;
import movies.services.impl.MovieJPADAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NetflixConfiguration.class)
public class DataModelTest {

    @Inject
    MovieJPADAO movieJPADAO;

    @Inject
    @Named("dataSource")
    DataSource ds;


    @Test
    public void getAllMovieTest(){

        //given
        List<Movie> moviesList = movieJPADAO.getAllMovies();

        //when
        for(Movie movie:moviesList){
//            System.out.println(movie.toString());
            System.out.println(movie);
        }

        //then
//        Assert.no(moviesList);
    }

    @Test
    public void saveMovieTest(){
        Movie superman = new Movie();
        movieJPADAO.saveMovie(superman);
    }

}
