package com.newoneplus.dresshub.Config;

import com.newoneplus.dresshub.Domain.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MyAuthentication extends UsernamePasswordAuthenticationToken {
    private static final long serialVersionUID = 1L;

    Member member;

    // ...임시
    public MyAuthentication (String id, String password, List<GrantedAuthority> grantedAuthorityList, Member member) {
        super(id, password, grantedAuthorityList);
        this.member = member;
    }

}

