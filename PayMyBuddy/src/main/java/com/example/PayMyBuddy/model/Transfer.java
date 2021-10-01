package com.example.PayMyBuddy.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transfer")
public class Transfer {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;
    @ManyToOne
    private User from;
    @ManyToOne
    private User to;
    private Double amountBeforeFee;
    private Double amountAfterFee;

}
