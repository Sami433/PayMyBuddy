package com.example.PayMyBuddy.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Account {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer accountId;
    private Double amount;
    private String iban;
    @OneToOne
    User user;

}

    /*
    public Account plus(double amount) {

        this.amount += amount;
        return this;
    }

    public Account minus(double amount) throws Exception {
        if (amount <= this.amount) {
            this.amount -= amount;
            return this;
        } else {
            throw new Exception("Not enough money");
        }
    }
}
*/