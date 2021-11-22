package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.model.Transfer;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.repository.AccountRepository;
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
    @Autowired
    private AccountRepository accountRepository;

    public void saveTransfer(Transfer transfer) throws Exception {

        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();

        int id = userService.findUserId(username);
        User from = userRepository.findById(id);



        int id2 = transfer.getTo().getId();
        User to = userRepository.findById(id2);



        transfer.setDate(LocalDateTime.now());
        transfer.setFrom(from);
        transfer.setTo(to);
        double amountTransfer= transfer.getAmountBeforeFee();
        if (transfer.getFrom().getAccount().getAmount() >= amountTransfer) {
            transfer.setAmountAfterFee(amountTransfer);
        } else {
            throw new Exception("Not enough money");
        }
        transferRepository.save(transfer);


       Account accountSender = transfer.getFrom().getAccount();
        double amount = accountSender.getAmount() - transfer.getAmountAfterFee();
        accountRepository.setAmountByUserId(amount, accountSender.getIban(), id);

        Account accountReceiver = transfer.getTo().getAccount();
        double amount2 = accountReceiver.getAmount() + transfer.getAmountBeforeFee();
        accountRepository.setAmountByUserId(amount2, accountReceiver.getIban(), id2);

    }
}


