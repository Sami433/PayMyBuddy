package com.example.PayMyBuddy.repository;

import com.example.PayMyBuddy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface AccountRepository extends JpaRepository<Account, Integer> {
    //Account findByIban(String iban);
    @Query("SELECT a FROM Account a WHERE a.user.id = ?1")
    public Account findByUserId(int id);
    @Transactional
    @Modifying
    @Query("update Account a set a.amount = ?1, a.iban=?2 where a.user.id = ?3")
    void setAmountByUserId(double amount, String iban, Integer id);

}
