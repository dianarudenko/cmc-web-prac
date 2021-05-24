package converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import dao.ServiceDAO;
import entity.Service;

@Component
public class ServiceConverter implements Converter<Object, Service> {
    @Autowired
    ServiceDAO serviceDAO;

    public Service convert(Object element) {
        if (element == null) return null;
        if (element.toString().isEmpty()) return null;
        Integer id = Integer.parseInt(element.toString());
        return serviceDAO.getByID(id);
    }
}