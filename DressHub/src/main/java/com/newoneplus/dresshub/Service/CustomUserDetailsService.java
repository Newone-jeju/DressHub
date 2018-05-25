package com.newoneplus.dresshub.Service;

import com.newoneplus.dresshub.Model.Member;
import com.newoneplus.dresshub.Model.SecurityMember;
import com.newoneplus.dresshub.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        return
                Optional.ofNullable(memberRepository.findByUid(uid))
                        .filter(m -> m!= null)
                        .map(m -> new SecurityMember(m)).get();
    }


}
