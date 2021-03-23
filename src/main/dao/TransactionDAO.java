package dao;

import java.sql.Date;
import java.util.List;

import dao.common.BasicDAO;
import entity.Bill;
import entity.Transaction;

public interface TransactionDAO extends BasicDAO<Transaction, Integer> {
    List<Transaction> getAllByBill(Bill bill);
    List<Transaction> getAllByBillAndDate(Bill bill, Date date);
}
