package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.model.Connection;
import com.example.PayMyBuddy.model.Transfer;
import com.example.PayMyBuddy.repository.AccountRepository;
import com.example.PayMyBuddy.repository.ConnectionRepository;
import com.example.PayMyBuddy.repository.TransferRepository;
import com.example.PayMyBuddy.service.CustomUserDetailsService;
import com.example.PayMyBuddy.service.TransferService;
import com.example.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TransferController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private TransferService transferService;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/transfer")
    //public String viewPage() {
    public ModelAndView listConnections(@ModelAttribute("transfer") Transfer transfer) {
        ModelAndView modelAndView = new ModelAndView("transfer");
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = customUserDetailsService.loadUserByUsername(springUser.getUsername()).getUsername();
        int id = userService.findUserId(username);
        List<Connection> listConnections = connectionRepository.findByUser1Id(id);
        modelAndView.addObject("listConnections", listConnections);
        List<Transfer> listTransfers = transferRepository.findByFromId(id);
        modelAndView.addObject("listTransfers", listTransfers);
        modelAndView.addObject("transfer", transfer);
        return modelAndView;
    }

    @GetMapping("/transfer/addConnection")
    public String viewAddConnection(Model model, Connection connection) {
        model.addAttribute("connection", connection);
        return "connection";
    }

    @PostMapping("/process_transfer")
    public ModelAndView transferMoney( @ModelAttribute("transfer") Transfer transfer) throws Exception {
        ModelAndView modelAndView = new ModelAndView("transfer_success");
        transferService.saveTransfer(transfer);
        return modelAndView;
    }


}
