package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.*;
import entity.*;

import org.springframework.ui.ModelMap;

@Controller
public class MainController {
    @Autowired
    private ServiceDAO serviceDAO;
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private ServicesClientsDAO servicesClientsDAO;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView mainPage(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="/services", method = RequestMethod.GET)
    public ModelAndView servicesPage(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("services");
        modelAndView.addObject("servicesList", serviceDAO.getAll());
        // if (serviceDAO.getAll() == null) {
        //    System.out.println("nulllll");
        // } else {
        //    System.out.println(serviceDAO.getAll().toString());
        // }
        return modelAndView;
    }

    @RequestMapping(value="/clients", method = RequestMethod.GET)
    public ModelAndView clientsPage(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clients");
        modelAndView.addObject("clientsList", clientDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ModelAndView clientPage(@RequestParam(value="id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client");
        Client client = clientDAO.getByID(id);
        List<ServicesClients> servicesList = servicesClientsDAO.getAllServicesForClient(client);
        modelAndView.addObject("client", client);
        modelAndView.addObject("servicesList", servicesList);
        modelAndView.addObject("activeServices", servicesClientsDAO.getActiveServicesForClient(client));
        return modelAndView;
    }

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public ModelAndView servicePage(@RequestParam(value="id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("service");
        Service service = serviceDAO.getByID(id);
        modelAndView.addObject("service", service);
        return modelAndView;
    }
}