package service.common;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import dao.common.BasicDAO;

@Transactional
public abstract class BasicServiceImpl<T, K extends Serializable> implements BasicService<T, K> {
    protected abstract BasicDAO<T, K> dao();

    @Override
    public T getByID(K id) {
        return dao().getByID(id);
    }

    @Override
    public List<T> getAll() {
        return dao().getAll();
    }

    @Override
    public void add(T obj) {
        dao().add(obj);
    }

    @Override
    public void update(T obj) {
        dao().update(obj);
    }

    @Override
    public void delete(T obj) {
        dao().delete(obj);
    }
}
