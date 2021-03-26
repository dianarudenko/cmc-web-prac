package dao.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateSessionFactoryUtil;

@SuppressWarnings("unchecked")
public class BasicDAOImpl<T, K extends Serializable> implements BasicDAO<T, K> {
    private final Class<T> type;
    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public BasicDAOImpl() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        Session currentSession;
        try {
            currentSession = sessionFactory.getCurrentSession();
        } catch (Exception e) {
            currentSession = sessionFactory.openSession();
        }
        return currentSession;
    }

    // @Autowired
    @Override
    public void setSessionFactory(SessionFactory newSessionFactory) {
        sessionFactory = newSessionFactory;
    }

    @Override
    public T getByID(K id) {
        Transaction transaction = getCurrentSession().beginTransaction();
        T item = null;
        try {
            item = getCurrentSession().get(type, id);
        } catch (Exception e) {
            transaction.rollback();
        }
        transaction.commit();
        return item;
    }

    @Override
    public List<T> getAll() {
        Transaction transaction = getCurrentSession().beginTransaction();
        List<T> items = null;
        try {
            CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(type);
            criteria.from(type);
            items = getCurrentSession().createQuery(criteria).getResultList();
        } catch (Exception e) {
            transaction.rollback();
        }
        transaction.commit();
        return items;
    }

    @Override
    public void add(T obj) {
        Transaction transaction = getCurrentSession().beginTransaction();
        try {
            getCurrentSession().save(obj);
        } catch (Exception e) {
            transaction.rollback();
        }
        transaction.commit();
    }

    @Override
    public void update(T obj) {
        Transaction transaction = getCurrentSession().beginTransaction();
        try {
            getCurrentSession().update(obj);
        } catch (Exception e) {
            transaction.rollback();
        }
        transaction.commit();
    }

    @Override
    public void delete(T obj) {
        Transaction transaction = getCurrentSession().beginTransaction();
        try {
            getCurrentSession().delete(obj);
        } catch (Exception e) {
            transaction.rollback();
        }
        transaction.commit();
    }
}
