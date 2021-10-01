package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.model.Transfer;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.repository.ConnectionRepository;
import com.example.PayMyBuddy.repository.TransferRepository;
import com.example.PayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private AccountService accountService;

    public void saveTransfer(Transfer transfer) throws Exception {
        //sender
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();
        int id = userService.findUserId(username);
        User from = userRepository.findById(id);
        //receiver
        int id2 = transfer.getTo().getId();
        User to = userRepository.findById(id2);
        transfer.setDate(LocalDateTime.now());
        transfer.setFrom(from);
        transfer.setTo(to);
        double amountAfterFee = transfer.getAmountBeforeFee() * 1.005;
        //here is the transfer method, if i can improve this or alter it in order to make it easier to run using user input from the main file, let me know
        if (transfer.getFrom().getAccount().getAmount() >= amountAfterFee) {
            transfer.setAmountAfterFee(amountAfterFee);
        } else {
            throw new Exception("Not enough money");
        }
        transferRepository.save(transfer);
        Account accountSender = transfer.getFrom().getAccount();
        accountService.updateAmountSender(accountSender);
        Account accountReceiver = transfer.getTo().getAccount();
        accountService.updateAmountReceiver(accountReceiver);
    }
}


