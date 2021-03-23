package dao;

import dao.common.BasicDAO;
import entity.Service;

public interface ServiceDAO extends BasicDAO<Service, Integer> {
    Service getByName(String name);
}
