package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.model.Connection;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.repository.ConnectionRepository;
import com.example.PayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class ConnectionService {
    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    public ConnectionService() {
    }


    public void add(Connection connection) {

        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String userx = springUser.getUsername();             // appeller le user x
        int id = userService.findUserId(userx);     // grace a la methode qui permet de trouver un user par son mail:  trouver l'id de l'user x
        User user1 = userRepository.findById(id);   // trouver l'id de l'user 1

        String email = connection.getUser2().getEmail();
        Optional<User> user2 = userRepository.findByEmail(email);

        user1.getEmail().equals(user2.get().getEmail());
        connection.setUser1(user1);
        connection.setUser2(user2.get());
        connectionRepository.save(connection);
    }
}









   /* public void add(Connection connection) {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();
        int id = userService.findUserId(username);
        User user1 = userRepository.findById(id);
        //Add Connection if user is already in DB and isn't duplicate
        List<Connection> listConnections = connectionRepository.findByUser1Id(id);
        boolean isAlreadyInConnection = false;
        String email = connection.getUser2().getEmail();
        Optional<User> user2 = userRepository.findByEmail(email);

        if (!user2.isEmpty()) {
            for (Connection conn : listConnections) {
                if (conn.getUser2().getEmail().equals(user2.get().getEmail())) {
                    isAlreadyInConnection = true;
                }
            }

            if (!isAlreadyInConnection) {
                List<User> userList = userRepository.findAll();
                for (User user : userList) {
                    if (user.getEmail().equals(user2.get().getEmail())) {
                        connection.setUser1(user1);
                        connection.setUser2(user2.get());
                        connectionRepository.save(connection);
                    }
                }
            }
        }

    }


}
            */


















