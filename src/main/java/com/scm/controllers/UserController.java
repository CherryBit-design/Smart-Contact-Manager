package com.scm.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {
    
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //user dashboard page
    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        System.out.println("User dashboard");
        return "user/dashboard";
    }

    @RequestMapping(value="/profile")
    public String userProfile() {
        //String username=Helper.getEmailOfLoggedInUser(authentication);
        //logger.info("User logged in:{}",username);
        System.out.println("User profile");
        return "user/profile";
    }
    
    //user add contacts page
    //user view contacts
    //user edit contact
    //user delete contact


}
