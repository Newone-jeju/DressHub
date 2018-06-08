package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.Member;
import com.newoneplus.dresshub.Model.MemberRole;
import com.newoneplus.dresshub.Repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
    @Autowired
    MemberRepository memberRepository;
    @GetMapping("/")
    public String showJoinForm() {
        return "join";
    }

    @PostMapping("")
    public String create(Member member){
        MemberRole role = new MemberRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        role.setRoleName("USER");
        member.setRoles(Arrays.asList(role));
        memberRepository.save(member);
        log.info("===============create member==============");
        return "redirect:/";
    }



}