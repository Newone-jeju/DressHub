package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/join")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String showJoinForm(Model model) {
        return "join";
    }

    @PostMapping("")
    public String create(@ModelAttribute @Valid @NotNull() User user) throws ClassNotFoundException, ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserType(1);
        user.setResisterDate(date);
        userService.save(user);
        return "redirect:/";
    }
}
