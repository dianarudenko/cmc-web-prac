package dao;

import java.util.List;

import dao.common.BasicDAO;
import entity.Client;
import entity.Service;
import entity.ServicesClients;

public interface ServicesClientsDAO extends BasicDAO<ServicesClients, Integer> {
    List<ServicesClients> getAllServicesForClient (Client client);
    List<ServicesClients> getActiveServicesForClient (Client client);
    void addServiceForClient (Service service, Client client);
    void disableServiceForClient (Service service, Client client);
}
