package com.newoneplus.dresshub.Model;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class AuthenticationToken {
    private String username;
    private List<MemberRole> roles;
    private String token;


    public AuthenticationToken(String uid, List<MemberRole> roles, String token) {
        this.username = uid;
        this.roles = roles;
        this.token = token;
    }
}
