package entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(name = "pgsql_enum", typeClass = resources.PostgreSQLEnumType.class)
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "bill")
    private Bill bill;

    public enum trans_type {incoming, outgoing};
    
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "type", columnDefinition = "enum('incoming', 'outgoing')")
    private trans_type type;

    private Timestamp date;
    private double sum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }
    public void setBill(Bill bill) {
        this.bill = bill;
    }
    public trans_type getType() {
        return type;
    }
    public void setType(trans_type type) {
        this.type = type;
    }
    public Timestamp getDate() {
        return date;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }
    public double getSum() {
        return sum;
    }
    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Transaction {\n\tid: " + id +
               "\n\tbill: " + ((bill == null) ? null : bill.getNumber()) +
               "\n\ttype: " + type +
               "\n\tdate: " + date +
               "\n\tsum: " + sum + "\n}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transaction other = (Transaction) obj;
        return (id == other.id) &&
               Objects.equals(type, other.type) &&
               Objects.equals(date, other.date) &&
               Objects.equals(sum, other.sum);
    }
}
