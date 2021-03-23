package dao.impl;

import javax.persistence.TypedQuery;

import org.hibernate.Transaction;

import dao.ServiceDAO;
import dao.common.BasicDAOImpl;
import entity.Service;

@SuppressWarnings("unchecked")
public class ServiceDAOImpl extends BasicDAOImpl<Service, Integer> implements ServiceDAO {
    @Override
    public Service getByName(String name) {
        Transaction transaction = getCurrentSession().beginTransaction();
        try {
            TypedQuery<Service> query = getCurrentSession().createQuery(
                "SELECT s FROM Service s WHERE name = :n"
            ).setParameter("n", name);
            Service result = query.getSingleResult();
            transaction.commit();
            return result;
        } catch(Exception e) {
            transaction.commit();
            return null;
        }
    }
}
