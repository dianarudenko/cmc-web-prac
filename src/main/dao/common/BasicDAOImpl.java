package dao.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

// import util.HibernateSessionFactoryUtil;

@Transactional
@SuppressWarnings("unchecked")
public class BasicDAOImpl<T, K extends Serializable> implements BasicDAO<T, K> {
    private final Class<T> type;
    // @Autowired
    private static SessionFactory sessionFactory;// = HibernateSessionFactoryUtil.getSessionFactory();

    @Autowired
    public void setSessionFactory(SessionFactory newSessionFactory) {
        sessionFactory = newSessionFactory;
    }

    public BasicDAOImpl() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T getByID(K id) {
        return getCurrentSession().get(type, id);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return getCurrentSession().createQuery(criteria).getResultList();
    }

    @Override
    public void add(T obj) {
        getCurrentSession().save(obj);
    }

    @Override
    public void update(T obj) {
        getCurrentSession().update(obj);
    }

    @Override
    public void delete(T obj) {
        getCurrentSession().delete(obj);
    }
}
