package com.newoneplus.dresshub.Config;

import com.newoneplus.dresshub.Domain.Member;
import com.newoneplus.dresshub.Service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AuthProvider implements AuthenticationProvider{
    @Autowired
    AuthorizationService authorizationService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        return authenticate(id, password);
    }

    private Authentication authenticate(String id, String password) throws AuthenticationException {
        Member member = null;
        try {
            member = authorizationService.login(id, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (member == null) return null;
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        String role = "";
        /**
         * 0 : 일반 사용자
         * 1 : 정회원(default)
         * 2 : 관리자
         * */
        switch (member.getUserType()) {
            case 0:
                role = "ROLE_NO";
                break;
            case 1:
                role = "ROLE_USER";
                break;
            case 2:
                role = "ROLE_ADMIN";
                break;
        }
        grantedAuthorityList.add(new SimpleGrantedAuthority(role));
        return new MyAuthentication(id, password, grantedAuthorityList, member);
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
