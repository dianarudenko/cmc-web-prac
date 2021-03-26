import org.testng.Assert;
import org.testng.annotations.Test;

import dao.BillDAO;
import dao.ClientDAO;
import dao.impl.BillDAOImpl;
import dao.impl.ClientDAOImpl;
import entity.Bill;
import entity.Client;

public class BillTest {
    @Test(priority = 0)
    public void getByClientTest() {
        BillDAO billDAO = new BillDAOImpl();
        ClientDAO clientDAO = new ClientDAOImpl();
        Client client = new Client();
        Bill bill = new Bill(10, client);
        client.setBill(bill);
        System.out.print("\tAdd new client with new bill");
        clientDAO.add(client);
        System.out.print("  OK\n\tTry to get added bill by client");
        Bill new_bill = billDAO.getByClient(client);
        Assert.assertNotNull(new_bill);
        Assert.assertTrue(new_bill.equals(bill));
        System.out.println("  OK");
    }
}
