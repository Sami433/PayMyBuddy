package com.example.PayMyBuddy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Connection> connections;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Account account;

}
q