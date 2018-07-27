package com.newoneplus.dresshub.Controller;


import com.newoneplus.dresshub.Model.AuthUser;
import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Repository.UserRepository;
import com.newoneplus.dresshub.Service.AuthorizationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    UserRepository userRepository;
    @PostMapping("/login")
    public String login(@RequestBody AuthUser auth, HttpServletResponse res){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String jwtString = null;
        try{
            User user = userRepository.findByUid(auth.getUid());
            if(bCryptPasswordEncoder.matches(auth.getPassword(), user.getPassword())){
                jwtString = Jwts.builder()
                        .setHeaderParam("typ", "JWT")
                        .setHeaderParam("issueDate", System.currentTimeMillis())
                        .setSubject("")
                        .claim("uid", user.getUid())
                        .claim("name", user.getName())
                        .signWith(SignatureAlgorithm.HS512, "Dresshub")
                        .compact();
            }else{
                res.setStatus(404);
            }
        }catch (EmptyResultDataAccessException e){
            res.setStatus(500);
        }

        return jwtString;

    }


}
