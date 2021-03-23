package dao;

import dao.common.BasicDAO;
import entity.Bill;
import entity.Client;

public interface BillDAO extends BasicDAO<Bill, Long> {
    Bill getByClient(Client client);
}
