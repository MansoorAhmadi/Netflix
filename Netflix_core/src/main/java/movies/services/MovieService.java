package movies.services;

import movies.datamodel.Movie;
import movies.exceptions.MoviesNotFoundException;
import movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements MovieRepository{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMoviesById(Long id) {
        Optional<Movie> opt = movieRepository.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            throw new MoviesNotFoundException("Movie with Id : "+id+" Not Found");
        }
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.delete(getMoviesById(id));
    }



    @Override
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

}
