package com.newoneplus.dresshub.Config;

import com.newoneplus.dresshub.Model.User;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class MyAuthentication extends UsernamePasswordAuthenticationToken {
    private static final long serialVersionUID = 1L;

    User user;

    public MyAuthentication (String id, String password, List<GrantedAuthority> grantedAuthorityList, User user) {
        super(id, password, grantedAuthorityList);
        this.user = user;
    }


}

