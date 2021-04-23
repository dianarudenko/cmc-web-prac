package dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.ClientDAO;
import dao.common.BasicDAOImpl;
import entity.Bill;
import entity.Client;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class ClientDAOImpl extends BasicDAOImpl<Client, Integer> implements ClientDAO {
    @Override
    public Client getByPhoneNumber(String phone_number) {
        Transaction transaction = getCurrentSession().beginTransaction();
        try {
            TypedQuery<Client> query = getCurrentSession().createQuery(
                "SELECT cl FROM Client cl WHERE phone_number = :phone_number"
            ).setParameter("phone_number", phone_number);
            Client result = query.getSingleResult();
            transaction.commit();
            return result;
        } catch(NoResultException e) {
            transaction.rollback();
            return null;
        } catch(Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<Client> getByFullName(String surname, String name, String middle_name) {
        Transaction transaction = getCurrentSession().beginTransaction();
        try {
            TypedQuery<Client> query;
            if (middle_name == null) {
                if (surname == null) {
                    query = getCurrentSession().createQuery(
                        "SELECT cl FROM Client cl WHERE name = :n"
                    ).setParameter("n", name);
                } else if (name == null) {
                    query = getCurrentSession().createQuery(
                        "SELECT cl FROM Client cl WHERE surname = :s"
                    ).setParameter("s", surname);
                } else {
                    query = getCurrentSession().createQuery(
                        "SELECT cl FROM Client cl WHERE name = :n and surname = :s"
                    ).setParameter("n", name)
                    .setParameter("s", surname);
                }
            } else if (surname == null) {
                if (name == null) {
                    query = getCurrentSession().createQuery(
                        "SELECT cl FROM Client cl WHERE middle_name = :m"
                    ).setParameter("m", middle_name);
                } else {
                    query = getCurrentSession().createQuery(
                        "SELECT cl FROM Client cl WHERE name = :n and middle_name = :m"
                    ).setParameter("n", name)
                    .setParameter("m", middle_name);
                }
            } else if (name == null) {
                query = getCurrentSession().createQuery(
                    "SELECT cl FROM Client cl WHERE surname = :s and middle_name = :m"
                ).setParameter("s", surname)
                .setParameter("m", middle_name);
            } else {
                query = getCurrentSession().createQuery(
                    "SELECT cl FROM Client cl WHERE name = :n and surname = :s and middle_name = :m"
                ).setParameter("n", name)
                .setParameter("s", surname)
                .setParameter("m", middle_name);
            }
            List<Client> result = query.getResultList();
            transaction.commit();
            return result;
        } catch(Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public Client getByBill(Bill bill) {
        Transaction transaction = getCurrentSession().beginTransaction();
        try {
            TypedQuery<Client> query = getCurrentSession().createQuery(
                "SELECT cl FROM Client cl WHERE bill = :b"
            ).setParameter("b", bill);
            Client result = query.getSingleResult();
            transaction.commit();
            return result;
        } catch(NoResultException e) {
            transaction.rollback();
            return null;
        } catch(Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
