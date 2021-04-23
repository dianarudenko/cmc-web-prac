package dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.ServicesClientsDAO;
import dao.common.BasicDAOImpl;
import entity.Client;
import entity.Service;
import entity.ServicesClients;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class ServicesClientsDAOImpl extends BasicDAOImpl<ServicesClients, Integer>
                                    implements ServicesClientsDAO {
    @Override
    public List<ServicesClients> getAllServicesForClient (Client client) {
        TypedQuery<ServicesClients> query = getCurrentSession().createQuery(
            "SELECT sc FROM ServicesClients sc WHERE client = :client ORDER BY id ASC"
        ).setParameter("client", client);
        return query.getResultList();
    }

    @Override
    public void disableServiceForClient (Service service, Client client) {
        Date nowDate = new Date((new java.util.Date()).getTime());
        getCurrentSession().createQuery(
            "UPDATE ServicesClients sc SET end_date = :d WHERE client = :c AND service = :s"
        ).setParameter("d", nowDate)
        .setParameter("c", client)
        .setParameter("s", service)
        .executeUpdate();
    }

    @Override
    public List<ServicesClients> getActiveServicesForClient (Client client) {
        TypedQuery<ServicesClients> query = getCurrentSession().createQuery(
            "SELECT sc FROM ServicesClients sc WHERE end_date = null AND client = :c"
        ).setParameter("c", client);
        return query.getResultList();
    }
}
