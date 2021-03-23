import org.testng.Assert;
import org.testng.annotations.Test;

import dao.BillDAO;
import dao.ClientDAO;
import dao.impl.BillDAOImpl;
import dao.impl.ClientDAOImpl;
import entity.Bill;
import entity.Client;

public class BillTest {
    @Test
    public void getByClientTest() {
        System.out.println("\n\ngetByClientTest:");
        BillDAO billDAO = new BillDAOImpl();
        ClientDAO clientDAO = new ClientDAOImpl();
        Client client = new Client();
        Bill bill = new Bill(10, client);
        client.setBill(bill);
        try {
            System.out.print("\tAdd new client with new bill");
            clientDAO.add(client);
            System.out.print("  OK\n\tTry to get added bill by client");
            Bill new_bill = billDAO.getByClient(client);
            Assert.assertNotNull(new_bill);
            Assert.assertTrue(new_bill.equals(bill));
            System.out.println("  OK");
            System.out.println("getByClientTest: OK");
        } catch (AssertionError e) {
            System.out.println("\nASSERT: BillTest.getByClientTest: " + e);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("\nERROR: BillTest.getByClientTest: " + e);
            Assert.assertTrue(false);
        }
    }
}
