package movies.repositories.repositoryGeneric;

import movies.datamodel.Movie;

import java.util.List;

public interface IRepository<T> {
    void create(T object);

    void update(T obj);

    void delete(T obj);

    List<Movie> search (T obj);
}
