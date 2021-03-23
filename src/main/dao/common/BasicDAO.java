package dao.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

public interface BasicDAO<T, K extends Serializable> {

    void setSessionFactory(SessionFactory sessionFactory);

    T getByID(K id);

    List<T> getAll();

    void add(T obj);

    void update(T obj);

    void delete(T obj);
}
