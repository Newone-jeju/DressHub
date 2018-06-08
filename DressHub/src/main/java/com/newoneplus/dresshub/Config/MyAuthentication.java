package com.newoneplus.dresshub.Config;

import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Service.AuthorizationService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Data
public class MyAuthentication extends UsernamePasswordAuthenticationToken {
    private static final long serialVersionUID = 1L;

    User user;

    // ...임시
    public MyAuthentication (String id, String password, List<GrantedAuthority> grantedAuthorityList, User user) {
        super(id, password, grantedAuthorityList);
        this.user = user;
    }



}

