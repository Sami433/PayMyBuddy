package com.example.PayMyBuddy.repository;

import com.example.PayMyBuddy.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Integer> {
    //public List<Connection> findAllConnections();
    @Query("SELECT c FROM Connection c WHERE c.user1.id = ?1")
    public List<Connection> findByUser1Id(int id);
}
