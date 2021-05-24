package converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import dao.ServicesClientsDAO;
import entity.ServicesClients;

@Component
public class ServicesClientsConverter implements Converter<Object, ServicesClients> {
    @Autowired
    ServicesClientsDAO servicesClientsDAO;

    public ServicesClients convert(Object element) {
        if (element.toString().isEmpty()) return null;
        Integer id = Integer.parseInt(element.toString());
        return servicesClientsDAO.getByID(id);
    }
}