package com.newoneplus.dresshub.Service;

import com.newoneplus.dresshub.Config.MyAuthentication;
import com.newoneplus.dresshub.Domain.Member;
import com.newoneplus.dresshub.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AuthorizationService {
    @Autowired
    MemberRepository memberRepository;

    public Member login(String uid, String password) throws SQLException, ClassNotFoundException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Member member = memberRepository.findByUid(uid);
        if(member == null) return null;
        if(bCryptPasswordEncoder.matches(password, member.getPassword()) == false) return null;
        return member;
    }

    public static Member getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof MyAuthentication)
            return ((MyAuthentication) authentication).getMember();
        return null;
    }

    public static void setCurrentUser(Member member) {
        ((MyAuthentication)
                SecurityContextHolder.getContext().getAuthentication()).setMember(member);
    }


}
