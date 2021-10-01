package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.repository.AccountRepository;
import com.example.PayMyBuddy.service.AccountService;
import com.example.PayMyBuddy.service.CustomUserDetailsService;
import com.example.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/account")
    public String accountForm(Model model, Account account) {
        model.addAttribute("account", account);
        return "account";
    }

    @GetMapping("/process_updating_account")
    public String processUpdatingAccount(Account account) {
        accountService.update(account);
        return "add_account_success";
    }


}
