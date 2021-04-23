package dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.TransactionDAO;
import dao.common.BasicDAOImpl;
import entity.Bill;
import entity.Transaction;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class TransactionDAOImpl extends BasicDAOImpl<Transaction, Integer> implements TransactionDAO {
    @Override
    public List<Transaction> getAllByBill(Bill bill) {
        org.hibernate.Transaction transaction = getCurrentSession().beginTransaction();
        try {
            TypedQuery<Transaction> query = getCurrentSession().createQuery(
                "SELECT t FROM Transaction t WHERE bill = :b ORDER BY date ASC"
            ).setParameter("b", bill);
            List<Transaction> result = query.getResultList();
            transaction.commit();
            return result;
        } catch(Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<Transaction> getAllByBillAndDate(Bill bill, Date date) {
        org.hibernate.Transaction transaction = getCurrentSession().beginTransaction();
        try {
            TypedQuery<Transaction> query = getCurrentSession().createQuery(
                "SELECT t FROM Transaction t WHERE bill = :b AND DATE(date) = :d ORDER BY date ASC"
            ).setParameter("b", bill)
            .setParameter("d", date);
            List<Transaction> result = query.getResultList();
            transaction.commit();
            return result;
        } catch(Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
