package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.*;
import entity.*;
import jakarta.validation.Valid;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

@Controller
public class MainController {
    @Autowired
    private ServiceDAO serviceDAO;
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private ServicesClientsDAO servicesClientsDAO;
    @Autowired
    private TransactionDAO transactionDAO;
    @Autowired
    private BillDAO billDAO;

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
        List<ServicesClients> servicesList = client.getServices();
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

    @RequestMapping(value = "/bill", method = RequestMethod.GET)
    public ModelAndView transactionsPage(@RequestParam(value="id") Long bill_num) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bill");
        Bill bill = billDAO.getByID(bill_num);
        Client client = bill.getClient();
        modelAndView.addObject("bill", bill);
        modelAndView.addObject("client", client);
        modelAndView.addObject("transactions", transactionDAO.getAllByBill(bill));
        return modelAndView;
    }

    @RequestMapping(value = "/delete_client", method = RequestMethod.GET)
    public ModelAndView deleteClient(@RequestParam(value="id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:clients");
        Client client = clientDAO.getByID(id);
        clientDAO.delete(client);
        return modelAndView;
    }

    @RequestMapping(value = "/add_client", method = RequestMethod.GET)
    public ModelAndView addClient() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editClient");
        modelAndView.addObject("client", new Client());
        Bill bill = new Bill();
        modelAndView.addObject("bill", bill);
        modelAndView.addObject("type", "add");
        modelAndView.addObject("services", serviceDAO.getAll());
        modelAndView.addObject("active_service", null);
        return modelAndView;
    }

    @RequestMapping(value = "/add_client", method = RequestMethod.POST)
    public ModelAndView addClientApply(@Valid @ModelAttribute("client") Client client,
                                       BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errors", result.getAllErrors());
            return modelAndView;
        }
        if ((client.getName() == null) || (client.getName().equals("")) ||
            (client.getSurname() == null) || (client.getSurname().equals("")) ||
            (client.getMiddle_name() == null) || (client.getMiddle_name().equals(""))) {
            
            modelAndView.setViewName("error");
            modelAndView.addObject("errors", null);
            return modelAndView;
        }
        Service active_service = null;
        if (client.getActive_service().getService() != null) {
            active_service = client.getActive_service().getService();
        }
        client.setActive_service(null);
        clientDAO.add(client);
        if (active_service != null) {
            servicesClientsDAO.addServiceForClient(active_service, client);
        }
        modelAndView.setViewName("redirect:client?id=" + client.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/edit_client", method = RequestMethod.GET)
    public ModelAndView editClient(@RequestParam(value="id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editClient");
        Client client = clientDAO.getByID(id);
        if (client.getActive_service() == null) {
            client.setActive_service(new ServicesClients());
        } else {
            client.getActive_service().setService(null);
        }
        modelAndView.addObject("client", client);
        modelAndView.addObject("type", "edit");
        Bill bill = new Bill();
        modelAndView.addObject("bill", bill);
        modelAndView.addObject("services", serviceDAO.getAll());
        List<ServicesClients> active_services =
            servicesClientsDAO.getActiveServicesForClient(client);
        if (active_services.size() != 0) {
            modelAndView.addObject("active_service", active_services.get(0));
        } else {
            modelAndView.addObject("active_service", null);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit_client", method = RequestMethod.POST)
    public ModelAndView editClientApply(@Valid @ModelAttribute("client") Client client,
                                        BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            return modelAndView;
        }
        if ((client.getName() == null) || (client.getName().equals("")) ||
            (client.getSurname() == null) || (client.getSurname().equals("")) ||
            (client.getMiddle_name() == null) || (client.getMiddle_name().equals(""))) {
            
            modelAndView.setViewName("error");
            modelAndView.addObject("errors", null);
            return modelAndView;
        }
        Service active_service = null;
        if (client.getActive_service().getService() != null) {
            List<ServicesClients> active_services =
                servicesClientsDAO.getActiveServicesForClient(client);
            active_service = client.getActive_service().getService();
            if (active_services.size() != 0) {
                client.getActive_service().setService(active_services.get(0).getService());
            } else {
                client.getActive_service().setService(null);
            }
            servicesClientsDAO.addServiceForClient(active_service, client);
        } else {
            client.setActive_service(null);
        }
        clientDAO.update(client);
        modelAndView.setViewName("redirect:client?id=" + client.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/disable_service", method = RequestMethod.GET)
    public ModelAndView disableService(@RequestParam(value="id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:client?id=" + id);
        Client client = clientDAO.getByID(id);
        Service active_service = null;
        List<ServicesClients> active_services =
            servicesClientsDAO.getActiveServicesForClient(client);
        if (active_services.size() != 0) {
            active_service = active_services.get(0).getService();
        }
        servicesClientsDAO.disableServiceForClient(active_service, client);
        return modelAndView;
    }

    @RequestMapping(value = "/delete_service", method = RequestMethod.GET)
    public ModelAndView deleteService(@RequestParam(value="id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:services");
        serviceDAO.delete(serviceDAO.getByID(id));
        return modelAndView;
    }

    @RequestMapping(value = "/add_service", method = RequestMethod.GET)
    public ModelAndView addService() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editService");
        modelAndView.addObject("service", new Service());
        return modelAndView;
    }

    @RequestMapping(value = "/add_service", method = RequestMethod.POST)
    public ModelAndView addServiceApply(@Valid @ModelAttribute("service") Service service,
                                       BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            return modelAndView;
        }
        if ((service.getName() == null) || (service.getName().equals(""))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errors", null);
            return modelAndView;
        }
        serviceDAO.add(service);
        modelAndView.setViewName("redirect:service?id=" + service.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/edit_service", method = RequestMethod.GET)
    public ModelAndView editService(@RequestParam(value="id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editService");
        modelAndView.addObject("service", serviceDAO.getByID(id));
        return modelAndView;
    }

    @RequestMapping(value = "/edit_service", method = RequestMethod.POST)
    public ModelAndView editServiceApply(@Valid @ModelAttribute("service") Service service,
                                         BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            return modelAndView;
        }
        if ((service.getName() == null) || (service.getName().equals(""))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errors", null);
            return modelAndView;
        }
        serviceDAO.update(service);
        modelAndView.setViewName("redirect:service?id=" + service.getId());
        return modelAndView;
    }
}