//package com.newoneplus.dresshub.Config;
//
//import com.newoneplus.dresshub.Model.User;
//import com.newoneplus.dresshub.Service.AuthorizationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class AuthProvider implements AuthenticationProvider {
//
//    @Autowired
//    AuthorizationService authorizationService;
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String id = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        return authenticate(id, password);
//    }
//
//    private Authentication authenticate(String id, String password) throws AuthenticationException {
//        User user = null;
//        try {
//            user = authorizationService.login(id, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (user == null) return null;
//        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
//        String role = "";
//        /**
//         * 0 : 일반 사용자
//         * 1 : 정회원
//         * 2 : 관리자
//         * */
//        switch (user.getUserType()) {
//            case 0:
//                role = "ROLE_NO";
//                break;
//            case 1:
//                role = "ROLE_USER";
//                break;
//            case 2:
//                role = "ROLE_ADMIN";
//                break;
//        }
//        grantedAuthorityList.add(new SimpleGrantedAuthority(role));
//        return new MyAuthentication(id, password, grantedAuthorityList, user);
//
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//
//
//    }
//}
