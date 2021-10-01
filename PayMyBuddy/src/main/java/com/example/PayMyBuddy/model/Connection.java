package com.example.PayMyBuddy.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Connection {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    User user1;
    @ManyToOne(cascade = CascadeType.ALL)
    User user2;
}