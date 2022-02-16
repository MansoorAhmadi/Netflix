package movies.services.impl;

import jdk.jfr.Name;
import movies.datamodel.Movie;
import movies.services.api.IDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class GenericJPADAO<T> implements IDAO<T> {

    @Inject
    @Name("sessionFactory")
    SessionFactory factory;

    @Override
    public void create(T object){
        Session session = factory.openSession();
        session.save(object);
        session.close();
    }

    @Override
    public void update(T obj){
        Session session = factory.openSession();
        session.update(obj);
        session.close();
    }

    @Override
    public void delete(T obj){
        Session session = factory.openSession();
        session.delete(obj);
        session.close();
    }

    @Override
    public List<Movie> search(T obj) {
        return null;
    }
}
