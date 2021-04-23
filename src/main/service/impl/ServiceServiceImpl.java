package service.impl;

import service.ServiceService;
import service.common.BasicServiceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ServiceDAO;
import dao.common.BasicDAO;

@Service
@Transactional
public class ServiceServiceImpl extends BasicServiceImpl<entity.Service, Integer> implements ServiceService {
    @Autowired
    private ServiceDAO serviceDAO;

    @Override
    protected BasicDAO<entity.Service, Integer> dao() {
        return serviceDAO;
    }
    
    @Override
    public entity.Service getByName(String name) {
        return serviceDAO.getByName(name);
    }
}
