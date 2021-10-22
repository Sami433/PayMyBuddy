package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.form.RegisterForm;
import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.repository.AccountRepository;
import com.example.PayMyBuddy.repository.UserRepository;
import com.example.PayMyBuddy.service.CustomUserDetailsService;
import com.example.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    @GetMapping("/home")
    public String UserProfile() {

        return "index";
    }

    @GetMapping("/register")
    public ModelAndView showRegistration(RegisterForm registerForm){
        ModelAndView modelAndView = new ModelAndView("sign_up_form");
        modelAndView.addObject("registerForm", new RegisterForm());
        return modelAndView;
    }
    @PostMapping("/process_register")
    public ModelAndView processRegistration(User user){
        ModelAndView modelAndView = new ModelAndView("register_success");
        userService.saveUser(user);
        return modelAndView;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }



    @GetMapping("/profile")
    public String profile(Model model){
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = springUser.getUsername();
        Optional<User> user = userRepository.findByEmail(username);
        model.addAttribute("user", user);
        int id = userService.findUserId(username);
        Account account = accountRepository.findByUserId(id);
        model.addAttribute("account", account);
        return "profile";

    }




}