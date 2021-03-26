//package test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import dao.BillDAO;
import dao.ClientDAO;
import dao.impl.BillDAOImpl;
import dao.impl.ClientDAOImpl;
import entity.Bill;
import entity.Client;

public class ClientTest {

    @Test(priority = 0)
    public void addClientTest () {
        Client client = new Client("+7(777)777-77-77", "surname", "name", "middle_name");
        Bill bill = new Bill(10000000, client);
        client.setBill(bill);
        ClientDAO clientDAO = new ClientDAOImpl();
        System.out.print("\tAdd new client");
        clientDAO.add(client);
        System.out.print("  OK\n\tFind added client by id");
        Client added_client = clientDAO.getByID(client.getId());
        System.out.print("  OK\n\tCompare clients");
        Assert.assertNotNull(added_client);
        Assert.assertTrue(added_client.equals(client));
        System.out.print("  OK\n\tCheck whether appears an error while trying to add a client with already existing phone number");
        boolean got_an_error = false;
        try {
            Client new_client = new Client("+7(777)777-77-77", "surname", "name", "middle_name");
            clientDAO.add(new_client);
        } catch (Exception e) {
            got_an_error = true;
        }
        Assert.assertTrue(got_an_error);
        System.out.println("  OK");
        Assert.assertNotNull(clientDAO.getAll());
        Assert.assertTrue(clientDAO.getAll().size() > 0);
    }

    @Test(priority = 1)
    public void findClientTest() {
        ClientDAO clientDAO = new ClientDAOImpl();
        Client client = new Client("+8(888)888-88-88", "surname", "name", "middle_name");
        Bill bill = new Bill(5000, client);
        client.setBill(bill);
        System.out.print("\tAdd new client");
        clientDAO.add(client);
        System.out.print("  OK\n\tFind added client by phone number");
        Client new_client = clientDAO.getByPhoneNumber("+8(888)888-88-88");
        Assert.assertNotNull(new_client);
        Assert.assertTrue(new_client.equals(client));
        System.out.print("  OK\n\tFind clients by full name of added client");
        List<Client> clients_list = clientDAO.getByFullName("surname", "name", "middle_name");
        Assert.assertNotNull(clients_list);
        Assert.assertTrue(clients_list.size() > 1);
        clients_list = clientDAO.getByFullName("surname", null, null);
        Assert.assertNotNull(clients_list);
        Assert.assertTrue(clients_list.size() > 1);
        clients_list = clientDAO.getByFullName(null, "name", null);
        Assert.assertNotNull(clients_list);
        Assert.assertTrue(clients_list.size() > 1);
        clients_list = clientDAO.getByFullName(null, null, "middle_name");
        Assert.assertNotNull(clients_list);
        Assert.assertTrue(clients_list.size() > 1);
        clients_list = clientDAO.getByFullName(null, "name", "middle_name");
        Assert.assertNotNull(clients_list);
        Assert.assertTrue(clients_list.size() > 1);
        clients_list = clientDAO.getByFullName("surname", null, "middle_name");
        Assert.assertNotNull(clients_list);
        Assert.assertTrue(clients_list.size() > 1);
        clients_list = clientDAO.getByFullName("surname", "name", null);
        Assert.assertNotNull(clients_list);
        Assert.assertTrue(clients_list.size() > 1);
        System.out.print("  OK\n\tFind client by bill");
        new_client = clientDAO.getByBill(bill);
        Assert.assertNotNull(new_client);
        Assert.assertTrue(new_client.equals(client));
        System.out.println("  OK");
    }

    @Test(priority = 2)
    public void deleteClientTest() {
        ClientDAO clientDAO = new ClientDAOImpl();
        BillDAO billDAO = new BillDAOImpl();
        System.out.print("\tFind added client by phone number");
        Client client = clientDAO.getByPhoneNumber("+7(777)777-77-77");
        Bill bill = client.getBill();
        System.out.print("  OK\n\tDelete added client");
        clientDAO.delete(client);
        System.out.print("  OK\n\tTry to find deleted client");
        Assert.assertNull(clientDAO.getByID(client.getId()));
        Assert.assertNull(clientDAO.getByPhoneNumber(client.getPhone_number()));
        System.out.print("  OK\n\tCheck whether related bill is deleted too");
        Assert.assertNull(billDAO.getByID(bill.getNumber()));
        System.out.println("  OK");
    }

    @Test(priority = 3)
    public void updateClientTest() {
        ClientDAO clientDAO = new ClientDAOImpl();
        Client client = new Client("+x(xxx)xxx-xx-xx", "surname",  "name", "middle_name");
        System.out.print("\tAdd new client");
        clientDAO.add(client);
        Client new_client = new Client("+y(yyy)yyy-yy-yy", "new_surname", "new_name", "new_middle_name");
        new_client.setId(client.getId());
        System.out.print("  OK\n\tUpdate the client");
        clientDAO.update(new_client);
        client = clientDAO.getByID(client.getId());
        System.out.print("  OK\n\tCompare updated and presented clients");
        Assert.assertNotNull(client);
        Assert.assertTrue(client.equals(new_client));
        System.out.println("  OK");
    }
}
