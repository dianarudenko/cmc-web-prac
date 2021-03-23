package entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "clients", uniqueConstraints = @UniqueConstraint(columnNames = "phone_number"))
public class Client implements Cloneable {
    public static final String PHONE_NUMBER = "^\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = PHONE_NUMBER)
    private String phone_number;

    public String name;
    public String surname;
    public String middle_name;

    @OneToOne(mappedBy = "client",
              cascade = CascadeType.ALL,
              orphanRemoval = true,
              fetch = FetchType.LAZY)
    @JoinColumn(name = "bill")
    private Bill bill;

    @OneToMany(mappedBy = "client",
               cascade = CascadeType.ALL,
               orphanRemoval = true,
               fetch = FetchType.EAGER)
    @OrderBy
    private List<ServicesClients> services = new ArrayList<>();

    public Client() {}

    public Client(@Pattern(regexp = PHONE_NUMBER) String phone_number,
            String surname, String name, String middle_name) {
        this.phone_number = phone_number;
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public List<ServicesClients> getServices() {
        return services;
    }

    public void setServices(List<ServicesClients> services) {
        this.services = services;
    }

    public void addService(Service service) {
        Date nowDate = new Date((new java.util.Date()).getTime());
        ServicesClients new_service = new ServicesClients(this, service, nowDate, null);
        services.add(new_service);
    }

    @Override
    public String toString() {
        return "Client {\n\tid: " + id +
               "\n\tphone_number: " + phone_number +
               "\n\tname: " + name +
               "\n\tsurname: " + surname +
               "\n\tmiddle_name: " + middle_name +
               "\n\tbill: " + ((bill == null) ? null : bill.getNumber()) + 
               "\n\tservices: " + services.toString() + "\n}";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client other = (Client) obj;
        return (id == other.id) &&
               ((bill == null) ? other.bill == null : (bill.getNumber() == other.bill.getNumber())) &&
               phone_number.equals(other.phone_number) &&
               name.equals(other.name) &&
               surname.equals(other.surname) &&
               middle_name.equals(other.middle_name);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}