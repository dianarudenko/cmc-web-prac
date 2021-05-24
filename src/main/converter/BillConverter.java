package converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import dao.BillDAO;
import entity.Bill;

@Component
public class BillConverter implements Converter<Object, Bill> {
    @Autowired
    BillDAO billDAO;

    public Bill convert(Object element) {
        if (element == null) return null;
        if (element.toString().isEmpty()) return null;
        Long number = Long.parseLong(element.toString());
        if (billDAO.getByID(number) == null) {
            Bill bill = new Bill(number);
            billDAO.add(bill);
            return bill;
        }
        return billDAO.getByID(number);
    }
}