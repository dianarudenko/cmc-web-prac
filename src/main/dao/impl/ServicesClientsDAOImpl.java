package dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Transaction;

import dao.ServicesClientsDAO;
import dao.common.BasicDAOImpl;
import entity.Client;
import entity.Service;
import entity.ServicesClients;

@SuppressWarnings("unchecked")
public class ServicesClientsDAOImpl extends BasicDAOImpl<ServicesClients, Integer>
                                    implements ServicesClientsDAO {
    @Override
    public List<ServicesClients> getAllServicesForClient (Client client) {
        Transaction transaction = getCurrentSession().beginTransaction();
        try {
            TypedQuery<ServicesClients> query = getCurrentSession().createQuery(
                "SELECT sc FROM ServicesClients sc WHERE client = :client ORDER BY id ASC"
            ).setParameter("client", client);
            List<ServicesClients> result = query.getResultList();
            transaction.commit();
            return result;
        } catch(Exception e) {
            transaction.commit();
            return null;
        }
    }

    @Override
    public void disableServiceForClient (Service service, Client client) {
        Transaction transaction = getCurrentSession().beginTransaction();
        try {
            Date nowDate = new Date((new java.util.Date()).getTime());
            getCurrentSession().createQuery(
                "UPDATE ServicesClients cs SET end_date = :d WHERE client = :c AND service = :s"
            ).setParameter("d", nowDate)
            .setParameter("c", client)
            .setParameter("s", service)
            .executeUpdate();
            transaction.commit();
        } catch(Exception e) {
            transaction.commit();
        }
    }
}
