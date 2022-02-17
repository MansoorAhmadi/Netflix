package movies.repositories;

import movies.datamodel.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    //JPA & CRUD
    //CRUD REPOSITORY EXTENDS JPA

    List<Movie> readAllLines();
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMoviesById(Long id);
    void deleteMovieById(Long id);
}
