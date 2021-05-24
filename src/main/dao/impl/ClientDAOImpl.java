package dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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
        try {
            TypedQuery<Client> query = getCurrentSession().createQuery(
                "SELECT cl FROM Client cl WHERE phone_number = :phone_number"
            ).setParameter("phone_number", phone_number);
            return query.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Client> getByFullName(String surname, String name, String middle_name) {
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
        return query.getResultList();
    }

    @Override
    public Client getByBill(Bill bill) {
        try {
            TypedQuery<Client> query = getCurrentSession().createQuery(
                "SELECT cl FROM Client cl WHERE bill = :b"
            ).setParameter("b", bill);
            return query.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
}
