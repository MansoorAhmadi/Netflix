package movies.services;

import movies.datamodel.Movie;
import movies.exceptions.MoviesNotFoundException;
import movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

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

}