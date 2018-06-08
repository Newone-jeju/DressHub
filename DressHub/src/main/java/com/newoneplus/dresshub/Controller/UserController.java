package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
@RequestMapping("/join")
public class UserController {
    //User controller
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String showJoinForm(Model model, Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "join";
    }

    @PostMapping("")
    public String create(@ModelAttribute @Valid User user) throws ClassNotFoundException, ParseException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserType(1);
        userRepository.save(user);
        return "redirect:/";
    }

}
