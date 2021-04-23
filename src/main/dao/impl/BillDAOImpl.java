package dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.BillDAO;
import dao.common.BasicDAOImpl;
import entity.Bill;
import entity.Client;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class BillDAOImpl extends BasicDAOImpl<Bill, Long> implements BillDAO {
    @Override
    public Bill getByClient(Client client) {
        try {
            TypedQuery<Bill> query = getCurrentSession().createQuery(
                "SELECT b FROM Bill b WHERE client = :c"
            ).setParameter("c", client);
            return query.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
}
