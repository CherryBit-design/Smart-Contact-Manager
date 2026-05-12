package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    
    
    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home Page handler");
        // sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtubeChannel", "Learn Code With Durgesh");
        model.addAttribute("githubRepo", "https://github.com/learncodewithdurgesh/");
        return "home";
    }

    //about route
    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("islogin", false);
        System.out.println("About page loading");
        return "about";
    }

    //services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services page loading");
        return "services";
    }

    //contact
    @RequestMapping("/contact")
    public String contact(){
        //return new String("contact");
        return "contact";
    }

    // login page
    @GetMapping("/login")
    public String login() {
        //return new String("login");
        return "login";
    }
    @GetMapping("/user/dashboard")
      public String userDashboard() {
        return "user/dashboard";
    }


    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    

    // register
    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        //default data to data
        // userForm.setName("Pushpa");
        // userForm.setAbout("hii hello");
        model.addAttribute("userForm",userForm);
        return "register";
    }
    
    //processing register
    @RequestMapping(value="/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        System.out.println("Processing registration");
        //fetch form data
        //UserForm
        System.out.println(userForm);
        //validate form data
        if(rBindingResult.hasErrors()){
            return "register";
        }
        //save to database

        //userservice
        //userForm --> user
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("\"C:\\Users\\Pushpa\\Desktop\\avatar-gender-neutral-silhouette-vector-600nw-2470054311.webp\"")
        // .build();
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("\"C:\\Users\\Pushpa\\Desktop\\avatar-gender-neutral-silhouette-vector-600nw-2470054311.webp\"");
        User savedUser = userService.saveUser(user);
        System.out.println("User saved: ");

        //messaage = 'Registration successful'

        //add the message
        Message message = Message.builder().content("Registration Successful").type(MessageType.blue).build();
        session.setAttribute("message",message);

        //redirect to login page
        return "redirect:/register";
    }
}
