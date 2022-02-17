package movies.dataModel;

import movies.config.HibernateConfiguration;
import movies.config.NetflixConfiguration;
import movies.datamodel.Movie;
import movies.services.impl.MovieJPADAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class DataModelTest {

    //Using LOGGER instead of system
    private static final Logger logger = LogManager.getLogger(DataModelTest.class);

    @Inject
    @Named("dataSource")
    DataSource dataSource;

    @Inject
    @Named("movieDAO")
    private MovieJPADAO movieJPADAO;


    @Test
    public void readCSVTest(){
        //given
        //when
        //then
        assertNotNull(movieJPADAO.readAllLines());
    }

    @Test
    public void getAllMovieTest(){

        //given
        List<Movie> moviesList = movieJPADAO.getAllMovies();

        //when
        for(Movie movie:moviesList){
           logger.info(movie.toString());
//            System.out.println(movie);
        }

        //then
        assertNotNull(moviesList);
    }

    @Test
    public void saveMovieTest(){

        //given
        Movie superman = new Movie();

        //when
        movieJPADAO.saveMovie(superman);


        //then
        assertNotNull(movieJPADAO);
        logger.info("superman movie is saved successfully!");
    }

}
