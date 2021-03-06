package dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.ServiceDAO;
import dao.common.BasicDAOImpl;
import entity.Service;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class ServiceDAOImpl extends BasicDAOImpl<Service, Integer> implements ServiceDAO {
    @Override
    public Service getByName(String name) {
        try {
            TypedQuery<Service> query = getCurrentSession().createQuery(
                "SELECT s FROM Service s WHERE name = :n"
            ).setParameter("n", name);
            Service result = query.getSingleResult();
            return result;
        } catch(NoResultException e) {
            return null;
        }
    }
}
