package movies.services.impl;

import jdk.jfr.Name;
import movies.datamodel.Movie;
import movies.exceptions.MoviesNotFoundException;
import movies.repositories.MovieRepository;
import movies.repositories.repositoryGeneric.IRepository;
import movies.services.api.IMovieDOA;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Repository
public class MovieJPADAO extends GenericJPADAO<Movie> implements IMovieDOA {


    @Autowired
    private MovieRepository movieRepository;


    public List<Movie> readAllLines() {

        String line = "";
        String[] parts = {};
        List<Movie> movies = new ArrayList<>();

        Movie movie = new Movie();
        File file = new File("Netflix_core/src/main/resources/moviesForPostgres.csv");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            parts = line.split(",");

            //class attributes: id - title - date
//          *******************************************
            movie.setTitle(parts[0]);
            movie.setDate(parts[1]);
            movies.add(movie);

//            *******************************************
            System.out.println(line);
        }
        return movies;
    }

        public List<Movie> search(Movie movie){
        Session session = factory.openSession();
        Query<Movie> query = session.createQuery("from TITLE from MOVIE where title =?", Movie.class);

        query.setParameter("TITLE", movie.getTitle());
        List<Movie> resultList = query.list();

        session.close();

        return resultList;
    }


    public Movie saveMovie(Movie movie) {
        //saving the movie in the database
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();

        //lambda expression to add movies - iteration
        movieRepository.getAllMovies().forEach(movies::add);
        return movies;
    }

    public Movie getMovieById(Long id) {
        Optional<Movie> opt = movieRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new MoviesNotFoundException("Movie with Id : " + id + " Not Found");
        }
    }



    public void deleteMovieById(Long id) {
        movieRepository.delete(getMovieById(id));
    }

    @Override
    public String toString() {
        return "MovieJPADAO{" +
                "factory=" + factory +
                ", factory=" + factory +
                ", movieRepository=" + movieRepository +
                '}';
    }
}
