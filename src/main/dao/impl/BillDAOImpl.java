package dao.impl;

import javax.persistence.TypedQuery;

import org.hibernate.Transaction;

import dao.BillDAO;
import dao.common.BasicDAOImpl;
import entity.Bill;
import entity.Client;

@SuppressWarnings("unchecked")
public class BillDAOImpl extends BasicDAOImpl<Bill, Long> implements BillDAO {
    @Override
    public Bill getByClient(Client client) {
        Transaction transaction = getCurrentSession().beginTransaction();
        try {
            TypedQuery<Bill> query = getCurrentSession().createQuery(
                "SELECT b FROM Bill b WHERE client = :c"
            ).setParameter("c", client);
            Bill result = query.getSingleResult();
            transaction.commit();
            return result;
        } catch(Exception e) {
            transaction.commit();
            return null;
        }
    }
}
