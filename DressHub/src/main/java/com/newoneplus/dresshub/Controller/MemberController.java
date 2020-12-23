package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Domain.Member;
import com.newoneplus.dresshub.Repository.MemberRepository;
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
public class MemberController {
    //User controller
    @Autowired
    MemberRepository memberRepository;

    @GetMapping
    public String showJoinForm(Model model, Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "join";
    }

    @PostMapping("")
    public String create(@ModelAttribute @Valid Member member) throws ClassNotFoundException, ParseException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setUserType(1);
        memberRepository.save(member);
        return "redirect:/";
    }

}
