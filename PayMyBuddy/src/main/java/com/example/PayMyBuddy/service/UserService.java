package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser (User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Account account = new Account();
        account.setAmount(0.0);
        account.setUser(user);
        account.setIban("not set");
        user.setAccount(account);
        userRepository.save(user);
    }
    public int findUserId (String username) {
        Optional<User> user = userRepository.findByEmail(username);
        return user.get().getId();
    }

}
