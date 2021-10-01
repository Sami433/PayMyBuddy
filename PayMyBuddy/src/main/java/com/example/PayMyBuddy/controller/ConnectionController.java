package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.model.Connection;
import com.example.PayMyBuddy.repository.ConnectionRepository;
import com.example.PayMyBuddy.service.ConnectionService;
import com.example.PayMyBuddy.service.CustomUserDetailsService;
import com.example.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ConnectionController {
    @Autowired
    private ConnectionService connectionService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private ConnectionRepository connectionRepository;

    @GetMapping("/connection")
    public String connectionForm(Model model, Connection connection) {
        model.addAttribute("connection", connection);
        return "connection";
    }

    @PostMapping("/process_connection")
    public String processConnection(Connection connection) {
        connectionService.add(connection);
        return "connection_success";
    }

    @GetMapping("/connections")
    public String listConnections(Model model) {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();
        int id = userService.findUserId(username);
        List<Connection> listConnections = connectionRepository.findByUser1Id(id);
        model.addAttribute("listConnections", listConnections);
        return "connections";
    }

}
