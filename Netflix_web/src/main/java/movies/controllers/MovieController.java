package movies.controllers;

import movies.datamodel.Movie;
import movies.exceptions.MoviesNotFoundException;
import movies.internalServerError.InternalServerError;
import movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "http://localhost:8080")
public class MovieController {

    //USE DTO CLASSES INSTEAD OF INTERNAL CLASSES

    @Autowired
    MovieRepository movieRepository;

    // Create a movie
    @PostMapping
    public ResponseEntity<Movie>
    createMovie(@RequestBody Movie movie) {
        try {
            Movie newMovie = new Movie();
            movie.getId();
            movie.getTitle();
            movie.getDate();
            movieRepository.save(newMovie);
            return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    // Update a movie
    @PutMapping("/{id}")
    public ResponseEntity<Movie>
    updateMovie(@PathVariable("id") Long id,
               @RequestBody Movie movie) {

        Optional<Movie> movieData = movieRepository.findById(id);
        if (movieData.isPresent()) {
            Movie _movie = movieData.get();
            _movie.setId(movie.getId());
            _movie.setTitle(movie.getTitle());
            _movie.setDate(movie.getDate());
            return new ResponseEntity<Movie>(movieRepository.save(_movie), HttpStatus.OK);
        } else {
            throw new MoviesNotFoundException("Invalid Movie!");
        }
    }

    // Get all movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {

        try {
            List<Movie> movies = new ArrayList<Movie>();
            movieRepository.findAll().forEach(movies::add);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }

    }

    // Get movies by ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie>
    getMovieByID(@PathVariable("id") Long id) {

        Optional<Movie> movieData = movieRepository.findById(id);
        if (movieData.isPresent()) {
            return new ResponseEntity<>(movieData.get(), HttpStatus.OK);
        } else {
            throw new MoviesNotFoundException("Invalid Movie!");
        }

    }

    // Delete a movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable("id") Long id) {

        Optional<Movie> movieData = movieRepository.findById(id);
        if (movieData.isPresent()) {
            movieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new MoviesNotFoundException("Invalid Movie!");
        }
    }

}
