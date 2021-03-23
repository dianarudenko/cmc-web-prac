package dao;

import java.util.List;

import dao.common.BasicDAO;
import entity.Bill;
import entity.Client;

public interface ClientDAO extends BasicDAO<Client, Integer> {
    public Client getByPhoneNumber(String phone_number);
    public List<Client> getByFullName(String surname, String name, String middle_name);
    public Client getByBill(Bill bill);
}
