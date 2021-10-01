package com.example.PayMyBuddy.repository;


import com.example.PayMyBuddy.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    @Query("SELECT t FROM Transfer t WHERE t.from.id = ?1")
    public List<Transfer> findByFromId(int id);
    @Query("SELECT t FROM Transfer t WHERE t.to.id = ?1")
    public List<Transfer> findByToId(int id);
    @Query("SELECT t FROM Transfer t WHERE t.from.id = ?1")
    public Optional<Transfer> findSenderByFromId(int id);
    @Query("SELECT t FROM Transfer t WHERE t.from.id = ?1")
    public Optional<Transfer> findReceiverByToId(int id);


}
