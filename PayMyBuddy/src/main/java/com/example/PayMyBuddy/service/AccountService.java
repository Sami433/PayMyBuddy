package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.model.Transfer;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.repository.AccountRepository;
import com.example.PayMyBuddy.repository.TransferRepository;
import com.example.PayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransferRepository transferRepository;

    public AccountService() {
    }

    public void add (Account account) {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();
        Optional<User> user = userRepository.findByEmail(username);
        int id = user.get().getId();
        double amount = user.get().getAccount().getAmount() + account.getAmount();
        String iban = account.getIban();
        accountRepository.setAmountByUserId(amount, iban, id);

    }

    public void withdraw (Account account) {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();
        Optional<User> user = userRepository.findByEmail(username);
        int id = user.get().getId();
        double amount = user.get().getAccount().getAmount() - account.getAmount();
        String iban = account.getIban();
        accountRepository.setAmountByUserId(amount, iban, id);


    }
}

