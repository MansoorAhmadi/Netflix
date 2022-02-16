package movies.services.api;

import movies.datamodel.Movie;

import java.util.List;

public interface IDAO<T> {

    //CORRESPONDING TO WHAT I HAVE LEARNED OUTSIDE JPA REPOSITORY EXTENSION

    void create(T object);

    void update(T obj);

    void delete(T obj);

    List<Movie> search (T obj);
}
