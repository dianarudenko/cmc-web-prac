package entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "services_clients")
public class ServicesClients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    private Date start_date;
    private Date end_date;

    public ServicesClients() {}

    public ServicesClients(Client client, Service service, Date start_date, Date end_date) {
        this.client = client;
        this.service = service;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }
    
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "Service/client {" +
               "\n\tclient: " + ((client == null) ? null : client.getId()) +
               "\n\tservice: " + ((service == null) ? null : service.getId()) + 
               "\n\tstart_date: " + ((start_date == null) ? null : start_date.toString()) +
               "\n\tend_date: " + ((end_date == null) ? null : end_date.toString()) + "\n}";
    }
}
