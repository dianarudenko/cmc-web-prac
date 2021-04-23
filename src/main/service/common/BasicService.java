package service.common;

import java.io.Serializable;
import java.util.List;

public interface BasicService<T, K extends Serializable> {
    
    T getByID(K id);

    List<T> getAll();

    void add(T obj);

    void update(T obj);

    void delete(T obj);
}
