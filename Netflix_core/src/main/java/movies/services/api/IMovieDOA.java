package movies.services.api;

import movies.datamodel.Movie;

import java.util.List;

public interface IMovieDOA extends IDAO<Movie> {

    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    void deleteMovieById(Long id);
}
