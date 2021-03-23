package entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "services", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private double cost;
    private boolean active;
    private Date creation_date;
    private int calls_min;
    private int internet_gb;
    private int sms_number;

    @OneToMany(mappedBy = "service",
               cascade = CascadeType.ALL,
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    @OrderBy
    private List<ServicesClients> clients = new ArrayList<>();

    public Service() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Date getCreation_date() {
        return creation_date;
    }
    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
    public int getCalls_min() {
        return calls_min;
    }
    public void setCalls_min(int calls_min) {
        this.calls_min = calls_min;
    }
    public int getInternet_gb() {
        return internet_gb;
    }
    public void setInternet_gb(int internet_gb) {
        this.internet_gb = internet_gb;
    }
    public int getSms_number() {
        return sms_number;
    }
    public void setSms_number(int sms_number) {
        this.sms_number = sms_number;
    }

    public List<ServicesClients> getClients() {
        return clients;
    }

    public void setClients(List<ServicesClients> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        Date nowDate = new Date((new java.util.Date()).getTime());
        ServicesClients new_client = new ServicesClients(client, this, nowDate, null);
        clients.add(new_client);
    }

    @Override
    public String toString() {
        return "Service {\n\tid: " + id +
               "\n\tname: '" + name +
               "'\n\tdescription: '" + description +
               "'\n\tcost: " + cost +
               "\n\tcreation_date: " + creation_date +
               "\n\tcalls_min: " + calls_min +
               "\n\tinternet_gb: " + internet_gb +
               "\n\tsms_number: " + sms_number + "\n}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Service other = (Service) obj;
        return (id == other.id) &&
               Objects.equals(name, other.name) &&
               Objects.equals(description, other.description) &&
               Objects.equals(cost, other.cost) &&
               (active == other.active) &&
               Objects.equals(creation_date, other.creation_date) &&
               (calls_min == other.calls_min) &&
               (internet_gb == other.internet_gb) &&
               (sms_number == other.sms_number);
    }
}