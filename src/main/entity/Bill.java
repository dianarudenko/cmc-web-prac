package entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    private long number;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    private double funds;
    private boolean active;

    public Bill() {
        number = 0;
        client = null;
        funds = 0;
        active = false;
    }

    public Bill(long number) {
        this.number = number;
    }

    public Bill(long number, Client client) {
        this.number = number;
        this.client = client;
    }

    public Bill(long number, Client client, double funds, boolean active) {
        this.number = number;
        this.client = client;
        this.funds = funds;
        this.active = active;
    }

    public long getNumber() {
        return number;
    }
    public void setNumber(long number) {
        this.number = number;
    }
    public double getFunds() {
        return funds;
    }
    public void setFunds(double funds) {
        this.funds = funds;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Bill {\n\tnumber: " + number +
               "\n\tclient: " + ((client == null) ? null : client.getId()) + 
               "\n\tfunds: " + funds +
               "\n\tactive: " + active + "\n}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bill other = (Bill) obj;
        return (number == other.number) &&
               ((client == null) ? other.client == null : (client.getId() == other.client.getId())) &&
               Objects.equals(funds, other.funds) &&
               (active == other.active);
    }
}
