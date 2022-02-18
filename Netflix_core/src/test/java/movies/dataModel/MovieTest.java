package movies.dataModel;

import movies.config.HibernateConfiguration;
import movies.datamodel.Movie;
import movies.repositories.MovieRepository;
import movies.services.impl.MovieJPADAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class MovieTest {

    //Using LOGGER instead of system
    private static final Logger logger = LogManager.getLogger(MovieTest.class);

    @Inject
    @Named("dataSource")
    DataSource dataSource;

    @Inject
    SessionFactory factory;

    @Inject
    @Named("movieDAO")
    MovieJPADAO movieJPADAO;

    @Inject
    private MovieRepository movieRepository;



    @Test
    public void readCSVTest(){
        //given
        //when
        //then
        assertNotNull(movieJPADAO.readAllLines());
        logger.info(movieJPADAO.readAllLines());
    }

    @Test
    public void getAllMovieTest(){
        //given
        List<Movie> moviesList = movieJPADAO.getAllMovies();

        //when
        logger.info(moviesList.toString());

        //then
        assertNotNull(moviesList);
    }

    @Test
    public void saveMovieTest(){
        //given
        Session session = factory.openSession();
        Movie movie = new Movie();
        movie.setTitle("SUPERMAN");
        movie.setDate("20-Sep-21");

        //when
        movieJPADAO.saveMovie(movie);

        //then
        assertNotNull(movieJPADAO);
        logger.info("superman movie is saved successfully!");
    }

}
