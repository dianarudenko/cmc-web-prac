package service;

import org.springframework.stereotype.Component;

import entity.Service;
import service.common.BasicService;

@Component
public interface ServiceService extends BasicService<Service, Integer> {
    Service getByName(String name);
}
