import java.sql.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import dao.ClientDAO;
import dao.ServiceDAO;
import dao.ServicesClientsDAO;
import dao.impl.ClientDAOImpl;
import dao.impl.ServiceDAOImpl;
import dao.impl.ServicesClientsDAOImpl;
import entity.Client;
import entity.Service;
import entity.ServicesClients;

@SuppressWarnings("all")
public class ServicesClientsTest {
    @Test
    public void getAllServicesForClientTest() {
        System.out.println("\n\ngetAllServicesForClientTest:");
        ServicesClientsDAO servicesClientsDAO = new ServicesClientsDAOImpl();
        ClientDAO clientDAO = new ClientDAOImpl();
        ServiceDAO serviceDAO = new ServiceDAOImpl();
        Service service1 = new Service();
        Service service2 = new Service();
        Client client = new Client();
        try {
            System.out.print("\tAdd two new services for a client");
            serviceDAO.add(service1);
            serviceDAO.add(service2);
            client.addService(service1);
            client.addService(service2);
            client.setName("servicesClientsTest");
            clientDAO.add(client);
            System.out.print("  OK\n\tTry to get the services for the client");
            List<ServicesClients> services = servicesClientsDAO.getAllServicesForClient(client);
            Assert.assertNotNull(services);
            Assert.assertTrue(services.size() == 2);
            System.out.println("  OK");
            System.out.println("getAllServicesForClientTest: OK");
        } catch (AssertionError e) {
            System.out.println("\nASSERT: ServicesClientsTest.getAllServicesForClientTest: " + e);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("\nERROR: ServicesClientsTest.getAllServicesForClientTest: " + e);
            Assert.assertTrue(false);
        }
    }

    @Test
    public void disableServiceForClientTest() {
        System.out.println("\n\ndisableServiceForClientTest:");
        ServicesClientsDAO servicesClientsDAO = new ServicesClientsDAOImpl();
        ClientDAO clientDAO = new ClientDAOImpl();
        try {
            System.out.print("\tGet added client");
            Client client = clientDAO.getByFullName(null, "servicesClientsTest", null).get(0);
            System.out.print("  OK\n\tGet 1 service of the client");
            Service service = client.getServices().get(0).getService();
            System.out.print("  OK\n\tTry to disable the service");
            servicesClientsDAO.disableServiceForClient(service, client);
            Date end_date = servicesClientsDAO.getAllServicesForClient(client).get(0).getEnd_date();
            java.util.Date java_util_end_date = new java.util.Date(end_date.getTime());
            java.util.Date nowDate = new java.util.Date();
            Assert.assertTrue(java_util_end_date.getDate() == nowDate.getDate());
            System.out.println("  OK");
            System.out.println("disableServiceForClientTest: OK");
        } catch (AssertionError e) {
            System.out.println("\nASSERT: ServicesClientsTest.disableServiceForClientTest: " + e);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("\nERROR: ServicesClientsTest.disableServiceForClientTest: " + e);
            Assert.assertTrue(false);
        }
    }
}
