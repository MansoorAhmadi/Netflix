package movies.repositories;

import movies.datamodel.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMoviesById(Long id);
    void deleteMovieById(Long id);
}
