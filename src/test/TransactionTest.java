import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import dao.BillDAO;
import dao.TransactionDAO;
import dao.impl.BillDAOImpl;
import dao.impl.TransactionDAOImpl;
import entity.Bill;
import entity.Transaction;

public class TransactionTest {
    @Test
    public void getAllByBillTest() {
        System.out.println("\n\ngetAllByBillTest:");
        TransactionDAO transactionDAO = new TransactionDAOImpl();
        BillDAO billDAO = new BillDAOImpl();
        Bill bill = new Bill(10000);
        Transaction transaction = new Transaction();
        try {
            System.out.print("\tCreate new bill, new transaction and add the new bill to it");
            billDAO.add(bill);
            transaction.setBill(bill);
            transactionDAO.add(transaction);
            System.out.print("  OK\n\tTry to get added transaction by bill");
            List<Transaction> transactions = transactionDAO.getAllByBill(bill);
            Assert.assertNotNull(transactions);
            Assert.assertTrue(transactions.size() == 1);
            Assert.assertTrue(transactions.get(0).equals(transaction));
            System.out.println("  OK");
            System.out.println("getAllByBillTest: OK");
        } catch (AssertionError e) {
            System.out.println("\nASSERT: TransactionTest.getAllByBillTest: " + e);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("\nERROR: TransactionTest.getAllByBillTest: " + e);
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getAllByBillAndDateTest() {
        System.out.println("\n\ngetAllByBillAndDateTest:");
        TransactionDAO transactionDAO = new TransactionDAOImpl();
        BillDAO billDAO = new BillDAOImpl();
        Bill bill = new Bill(10001);
        Transaction transaction = new Transaction();
        try {
            System.out.print("\tAdd new transaction");
            Timestamp date = new Timestamp((new java.util.Date()).getTime());
            billDAO.add(bill);
            transaction.setBill(bill);
            transaction.setDate(date);
            transactionDAO.add(transaction);
            Date nowDate = new Date((new java.util.Date()).getTime());
            System.out.print("  OK\n\tTry to get the transaction by bill and date");
            List<Transaction> transactions = transactionDAO.getAllByBillAndDate(bill, nowDate);
            Assert.assertNotNull(transactions);
            Assert.assertTrue(transactions.size() == 1);
            Assert.assertTrue(transaction.equals(transactions.get(0)));
            System.out.println("  OK");
            System.out.println("getAllByBillAndDateTest: OK");
        } catch (AssertionError e) {
            System.out.println("\nASSERT: TransactionTest.getAllByBillAndDateTest: " + e);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("\nERROR: TransactionTest.getAllByBillAndDateTest: " + e);
            Assert.assertTrue(false);
        }
    }
}
