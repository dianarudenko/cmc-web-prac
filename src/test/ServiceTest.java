import org.testng.Assert;
import org.testng.annotations.Test;

import dao.ServiceDAO;
import dao.impl.ServiceDAOImpl;
import entity.Service;

public class ServiceTest {
    @Test(priority = 0)
    public void getByNameTest() {
        ServiceDAO serviceDAO = new ServiceDAOImpl();
        Service service = new Service();
        service.setName("testService");
        System.out.print("\tAdd new service");
        serviceDAO.add(service);
        System.out.print("  OK\n\tTry to get added service by name");
        Service new_service = serviceDAO.getByName(service.getName());
        Assert.assertNotNull(new_service);
        Assert.assertTrue(new_service.equals(service));
        System.out.println("  OK");
    }
}
